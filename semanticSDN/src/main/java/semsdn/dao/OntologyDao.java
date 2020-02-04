package semsdn.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.jena.ontology.OntDocumentManager;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.Rule;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import semsdn.constant.Constant;
import semsdn.devices.Host;
import semsdn.devices.Link;
import semsdn.devices.Switch;

/**
 * DAO层：主要提供基本的操作，例如本体中信息的增删改查
 * @author ssj
 *
 */
//该注解的作用是，在其他的类需要使用的时候提供@Autowired注解就可以实现对象的注入，无需使用new关键字创建对象
@Component  
public class OntologyDao {
	
	// 本体模型，每次操作本体需要将本体读入model数据容器
	OntModel model;
	// 具有推理功能的Model数据容器
	InfModel myinf;
	// 本体文档管理器，有多个本体的时候，需要在其中注册，才能实现多个本体联合查询，推理等
	OntDocumentManager fileManager;

	public OntologyDao() {
		// 创建本体模型
		model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		// 创建本体文档管理器
		fileManager = model.getDocumentManager();
		// 向文档管理器中注册该本体
		fileManager.addAltEntry(Constant.SDN_NAMESPACES, Constant.PATH_SDN);
		// 将本体读入到model中
		model.read(Constant.PATH_SDN);
		// 根据规则文件，将规则读到内存中
		List<Rule> rules = Rule.rulesFromURL(Constant.PATH_RULES);
		// 利用规则创建推理机
		Reasoner myreasoner = new GenericRuleReasoner(rules);
		// 创建带有推理功能的model
		myinf = ModelFactory.createInfModel(myreasoner, model);
	}
	
	/**
	 * 测试语句
	 * 	SELECT ?subject ?object
		WHERE { ?subject rdfs:subClassOf ?object }
	 * @param sparql
	 * @return
	 */
	public String getResourceInfo(String sparql) {
		String prefix = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + 
				"PREFIX owl: <http://www.w3.org/2002/07/owl#> " + 
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " + 
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " + 
				"PREFIX sdn: <http://www.semanticweb.org/ssj/ontologies/2018/10/semsdn#> ";
		Query query = QueryFactory.create(prefix + sparql);
		QueryExecution qe = QueryExecutionFactory.create(query, myinf);
		ResultSet results = qe.execSelect();
		List<String> colsName = results.getResultVars();
		JSONArray jsonArr = new JSONArray();
		while (results.hasNext()) {
			JSONObject obj = new JSONObject();
			QuerySolution querySolution = results.next();
			for(String temp : colsName) {
				obj.put(temp, querySolution.get(temp).toString());
			}
			jsonArr.add(obj);
		}
		return jsonArr.toString();
	}
	
	/**
	 * 获取每个交换机的端口号，并将其存放到map中，作用实在在管理页面中实现监测哪个交换机哪个端口的选择
	 * @return
	 */
	public Map<String, List<Integer>> getAllSwWithPort() {
		Map<String, List<Integer>> map = new HashMap<>();
		List<String> sw = getAllSwName();
		for(String s : sw) {
			map.put(s, getAllPort(s));
		}
		return map;
	}
	
	/**
	 * 得到某个交换机的端口列表
	 * @param sw
	 * @return
	 */
	private List<Integer> getAllPort(String sw) {
		List<Integer> sp = new ArrayList<>();
		String querySp  = "PREFIX sdn: <http://www.semanticweb.org/ssj/ontologies/2018/10/semsdn#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "SELECT   ?pn "
				+ "WHERE {sdn:" + sw + " sdn:consist_of ?p . ?p sdn:port_id ?pn}";
		Query query = QueryFactory.create(querySp);
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		while (results.hasNext()) {
			QuerySolution querySolution = results.next();
			sp.add(querySolution.getLiteral("pn").getInt());
		}
		qe.close();
		return sp;
	}
	
	/**
	 * 返回所有的交换机实例的名字
	 * @return
	 */
	private List<String> getAllSwName() {
		List<String> sw = new ArrayList<>();
		// 创建查询的SPARQL语句
		String querySw  = "PREFIX sdn: <http://www.semanticweb.org/ssj/ontologies/2018/10/semsdn#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "SELECT  ?sw "
				+ "WHERE { ?sw rdf:type sdn:OVS}";
		// 创建查询对象
		Query query = QueryFactory.create(querySw);
		// 创建查询执行器
		QueryExecution qe = QueryExecutionFactory.create(query, myinf);
		// 执行，返回结果集
		ResultSet results = qe.execSelect();
		// 遍历结果集，取出结果，保存到List中
		while (results.hasNext()) {
			QuerySolution querySolution = results.next();
			sw.add(querySolution.get("sw").toString().substring(57, 80));
		}
		// 关闭查询执行器
		qe.close();
		return sw;
	}
	
	/**
	 * 获取链路信息
	 * @return
	 */
	public List<Link> getAllLinks() {
		List<Link> links = new ArrayList<>();
		String queryString  = "PREFIX sdn: <http://www.semanticweb.org/ssj/ontologies/2018/10/semsdn#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "SELECT ?srcs ?srcp ?dsts ?dstp ?type ?direction ?latency "
				+ "WHERE {"
				+ "?link rdf:type sdn:Link ."
				+ "?link sdn:src_swid ?srcs ."
				+ "?link sdn:src_port ?srcp ."
				+ "?link sdn:dst_swid ?dsts ." 
				+ "?link sdn:dst_port ?dstp ." 
				+ "?link sdn:type ?type ." 
				+ "?link sdn:direction ?direction ." 
				+ "?link sdn:latency ?latency ."
				+ "}";
		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, myinf);
		ResultSet results = qe.execSelect();
		System.out.println(results.getResultVars());
		String one = null;
		int two = 0;
		String three = null;
		int four = 0;
		String five = null;
		String six = null;
		long seven = 0;
		while (results.hasNext()) {
			QuerySolution querySolution = results.next();
			one = querySolution.get("srcs").toString();
			two = querySolution.getLiteral("srcp").getInt();
			three = querySolution.get("dsts").toString();
			four = querySolution.getLiteral("dstp").getInt();
			five = querySolution.get("type").toString();
			six = querySolution.get("direction").toString();
			seven = querySolution.getLiteral("latency").getLong();
			links.add(new Link(one, two, three, four, five, six, seven));
		}
		qe.close();
		return links;
	}

	
	/**
	 * 功能：获取网络中的所有主机
	 * @return 主机列表
	 */
	public List<Host> getAllHosts() {
		List<Host> hosts = new ArrayList<>();
		String queryString = "PREFIX sdn: <http://www.semanticweb.org/ssj/ontologies/2018/10/semsdn#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "SELECT ?ip ?mac ?swid ?swp ?lastSeen WHERE { "
				+ "?h rdf:type sdn:Host ."
				+ "?h sdn:ip_address ?ip ."
				+ "?h sdn:mac_address ?mac ."
				+ "?h sdn:connect_sw_id ?swid ."
				+ "?h sdn:connect_sw_port ?swp ."
				+ "?h sdn:lastSeen ?lastSeen ."
				+ "}";
		Query query = QueryFactory.create(queryString);
		QueryExecution queryExecution = QueryExecutionFactory.create(query, model);
		ResultSet results = queryExecution.execSelect();
		String one = null;
		String two = null;
		String three = null;
		int four = 0;
		long five = 0;
		while (results.hasNext()) {
			QuerySolution querySolution = results.next();
			one = querySolution.get("ip").toString();
			two = querySolution.get("mac").toString();
			three = querySolution.get("swid").toString();
			four = querySolution.getLiteral("swp").getInt();
			five = querySolution.getLiteral("lastSeen").getLong();
			hosts.add(new Host(one, two, three, four, five));
		}
		queryExecution.close();
		return hosts;
	}

	/**
	 * 获取所有的交换机
	 * @return
	 */
	public List<Switch> getAllSwitches() {
		List<Switch> switches = new ArrayList<>();
		String queryString = "PREFIX sdn: <http://www.semanticweb.org/ssj/ontologies/2018/10/semsdn#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "SELECT ?swid ?ip ?connectedSince ?openFlowVersion "
				+ "WHERE {"
				+ "?s rdf:type sdn:OVS ."
				+ "?s sdn:ip_address ?ip ."
				+ "?s sdn:connectedSince ?connectedSince ."
				+ "?s sdn:openFlowVersion ?openFlowVersion ."
				+ "?s sdn:switch_id ?swid ."
				+ "}";
		Query query = QueryFactory.create(queryString);
		QueryExecution queryExecution = QueryExecutionFactory.create(query, model);
		ResultSet results = queryExecution.execSelect();
		String one = null;
		String two = null;
		long three = 0;
		String four = null;
		while (results.hasNext()) {
			QuerySolution querySolution = results.next();
			one = querySolution.get("swid").toString();
			two = querySolution.get("ip").toString();
			three = querySolution.getLiteral("connectedSince").getLong();
			four = querySolution.get("openFlowVersion").toString();
			switches.add(new Switch(one, two, four, three));
		}
		queryExecution.close();
		return switches;
	}

	

}
