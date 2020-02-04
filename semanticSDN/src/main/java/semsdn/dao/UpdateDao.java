package semsdn.dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntDocumentManager;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import semsdn.constant.Constant;

/**
 * 主要完成对本体的增删查改操作
 * @author ssj
 *
 */
//该注解的作用是，在其他的类需要使用的时候提供@Autowired注解就可以实现对象的注入，无需使用new关键字创建对象
@Component
public class UpdateDao {

	private static Logger logger = LoggerFactory.getLogger(UpdateDao.class);
	
	OntModel model;
	OntDocumentManager fileManager;

	public UpdateDao() {
		model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		fileManager = model.getDocumentManager();
		fileManager.addAltEntry(Constant.SDN_NAMESPACES, Constant.PATH_SDN);
		model.read(Constant.PATH_SDN);
	}
	
	/**
	 * 在model中所作出的修改持久化到本体中
	 */
	public void writeIntoOntology() {
		FileOutputStream fileos = null;
		try {
			fileos = new FileOutputStream(Constant.PATH_SDN);
			model.write(fileos, "RDF/XML");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(fileos != null) {
				try {
					fileos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	/**
	 * 此处根据实际的需要，删除的是所有的实例，也可以根据需要删除某一个实例.......
	 * @throws IOException
	 */
	public void deleteIndividuals() {
		ExtendedIterator<Individual> iter = model.listIndividuals();
		while(iter.hasNext()) {
			Individual indi = iter.next();
			indi.remove();
		}
		writeIntoOntology();
	}
	
	/**
	 * 根据链路信息在本体中构建端口实例以及链路实例，并且交换机之间的连接关系陈述添加到本体中。
	 * @param links
	 */
	public void createIndividualForLinkAndPort(String links) {
		logger.info("进入createIndividualForLinkAndPort()方法，创建端口实例，链路实例，并且初始化连接关系！");
		// 从model中取出类，在取的时候注意要使用全限定名
		OntClass cls = model.getOntClass(Constant.LINK_CLASS_NAMESPACES);
		// 从model中取出性质
		Property src = model.getProperty(Constant.SDN_NAMESPACES + "src_swid");
		Property srcp = model.getProperty(Constant.SDN_NAMESPACES + "src_port");
		Property dst = model.getProperty(Constant.SDN_NAMESPACES + "dst_swid");
		Property dstp = model.getProperty(Constant.SDN_NAMESPACES + "dst_port");
		Property ty = model.getProperty(Constant.SDN_NAMESPACES + "type");
		Property dir = model.getProperty(Constant.SDN_NAMESPACES + "direction");
		Property lat = model.getProperty(Constant.SDN_NAMESPACES + "latency");
		
		OntClass pcls = model.getOntClass(Constant.PORT_CLASS_NAMESPACES);
		Property portId = model.getProperty(Constant.SDN_NAMESPACES + "port_id");
		
		Property connect = model.getProperty(Constant.SDN_NAMESPACES + "connect");
		Property consist = model.getProperty(Constant.SDN_NAMESPACES + "consist_of");
		
		
		
		logger.info("解析含有链路信息的json字符串！");
		JSONArray jsonArray = JSONArray.parseArray(links);
		for(int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			String srcSwitch = jsonObject.getString("src-switch");
			int srcPort = jsonObject.getIntValue("src-port");
			String dstSwitch = jsonObject.getString("dst-switch");
			int dstPort = jsonObject.getIntValue("dst-port");
			String type = jsonObject.getString("type");
			String dire = jsonObject.getString("direction");
			long latency = jsonObject.getLongValue("latency");
			logger.info("给链路实例赋值属性值开始");
			RDFNode srcNode = model.createLiteral(srcSwitch);
			RDFNode scrpNode = model.createLiteral(String.valueOf(srcPort));
			RDFNode dstNode = model.createLiteral(dstSwitch);
			RDFNode dstpNode = model.createLiteral(String.valueOf(dstPort));
			RDFNode typeNode = model.createLiteral(type);
			RDFNode direNode = model.createLiteral(dire);
			RDFNode latencyNode = model.createLiteral(String.valueOf(latency));
			Individual sw = model.createIndividual(Constant.SDN_NAMESPACES + 'L' + i + srcPort + dstPort, cls);
			sw.setPropertyValue(src, srcNode);
			sw.setPropertyValue(srcp, scrpNode);
			sw.setPropertyValue(dst, dstNode);
			sw.setPropertyValue(dstp, dstpNode);
			sw.setPropertyValue(ty, typeNode);
			sw.setPropertyValue(dir, direNode);
			sw.setPropertyValue(lat, latencyNode);
			logger.info("给链路实例赋值属性值结束");
			logger.info("给端口实例赋值属性值并且初始化和交换机的关系开始");
			Individual sp = model.createIndividual(Constant.SDN_NAMESPACES + srcSwitch.charAt(21) + srcSwitch.charAt(22) + srcPort, pcls);
			sp.setPropertyValue(portId, scrpNode);
			Individual dp = model.createIndividual(Constant.SDN_NAMESPACES + dstSwitch.charAt(21) + dstSwitch.charAt(22) + dstPort, pcls);
			dp.setPropertyValue(portId, dstpNode);
			logger.info("给端口实例赋值属性值并且初始化和交换机的关系结束");
			logger.info("给端口和链路关系初始化开始");
			model.add(sw, connect, sp);
			model.add(sw, connect, dp);
			Individual ssw = model.getIndividual(Constant.SDN_NAMESPACES + srcSwitch);
			Individual dsw = model.getIndividual(Constant.SDN_NAMESPACES + dstSwitch);
			model.add(ssw, consist, sp);
			model.add(dsw, consist, dp);
			logger.info("给端口和链路关系初始化结束");
		}
		logger.info("持久化到本体！");
		writeIntoOntology();
		logger.info("退出createIndividualForLinkAndPort()方法");
	}
	
	/**
	 * ͨ通过解析json字符串，在本体中创建主机实例
	 * @param hosts 包含主机的json字符串
	 */
	public void createIndividualForHost(String hosts) {
		logger.info("进入createIndividualForHost()方法，创建host实例，并且赋初值！");
		logger.info("取出主机类的相关的属性！");
		OntClass cls = model.getOntClass(Constant.HOST_CLASS_NAMESPACES);
		Property ma = model.getProperty(Constant.SDN_NAMESPACES + "mac_address");
		Property ip = model.getProperty(Constant.SDN_NAMESPACES + "ip_address");
		Property csid = model.getProperty(Constant.SDN_NAMESPACES + "connect_sw_id");
		Property csp = model.getProperty(Constant.SDN_NAMESPACES + "connect_sw_port");
		Property ls = model.getProperty(Constant.SDN_NAMESPACES + "lastSeen");
		logger.info("解析交换机json字符串！并建立实例，赋初值");
		JSONArray jsonArray = JSONArray.parseArray(hosts);
		for(int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			String ipv4 = jsonObject.getString("ipv4");
			ipv4 = ipv4.substring(2, ipv4.length() - 2);
			String mac = jsonObject.getString("mac");
			mac = mac.substring(2, mac.length() - 2);
			long lastSeen = jsonObject.getLongValue("lastSeen");
			String swid = jsonObject.getString("swid");
			int pid = jsonObject.getIntValue("pid");
			logger.info(ipv4 + " " + mac + " " + swid + " " + pid + " " + lastSeen);
			RDFNode sNode = model.createLiteral(ipv4);
			RDFNode iNode = model.createLiteral(mac);
			RDFNode cNode = model.createLiteral(String.valueOf(pid));
			RDFNode lastNode = model.createLiteral(String.valueOf(lastSeen));
			RDFNode swNode = model.createLiteral(swid);
			Individual sw = model.createIndividual(Constant.SDN_NAMESPACES + ipv4, cls);
			sw.setPropertyValue(ma, iNode);
			sw.setPropertyValue(ip, sNode);
			sw.setPropertyValue(csid, swNode);
			sw.setPropertyValue(csp, cNode);
			sw.setPropertyValue(ls, lastNode);
		}
		logger.info("持久化到本体！");
		writeIntoOntology();
		logger.info("退出createIndividualForHost()方法");
	}
	
	/**
	 * 通过解析json字符串，在本体中创建交换机实例
	 * @param switches 
	 */
	public void createIndividualForSwitch(String switches) {
		logger.info("进入createIndividualForSwitch()方法，创建switch实例，并且赋初值！");
		logger.info("取出交换机类以及相关的数据属性！");
		OntClass cls = model.getOntClass(Constant.SWITCH_CLASS_NAMESPACES);
		Property sId = model.getProperty(Constant.SDN_NAMESPACES + "switch_id");
		Property ip = model.getProperty(Constant.SDN_NAMESPACES + "ip_address");
		Property cs = model.getProperty(Constant.SDN_NAMESPACES + "connectedSince");
		Property ofVersion = model.getProperty(Constant.SDN_NAMESPACES + "openFlowVersion");
		logger.info("解析交换机json字符串！并建立实例，赋初值");
		JSONArray jsonArray = JSONArray.parseArray(switches);
		for(int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			String switch_id = jsonObject.getString("switchDPID");
			String ip_address = jsonObject.getString("inetAddress");
			Long connectSince = jsonObject.getLong("connectedSince");
			String openFlowVersion = jsonObject.getString("openFlowVersion");
			logger.info(switch_id + " " + ip_address + " " + connectSince + " " + openFlowVersion);
			RDFNode sNode = model.createLiteral(switch_id);
			RDFNode iNode = model.createLiteral(ip_address);
			RDFNode cNode = model.createLiteral(String.valueOf(connectSince));
			RDFNode oNode = model.createLiteral(openFlowVersion);
			Individual sw = model.createIndividual(Constant.SDN_NAMESPACES + switch_id, cls);
			sw.setPropertyValue(sId, sNode);
			sw.setPropertyValue(ip, iNode);
			sw.setPropertyValue(cs, cNode);
			sw.setPropertyValue(ofVersion, oNode);
		}
		logger.info("持久化到本体！");
		writeIntoOntology();
		logger.info("退出createIndividualForSwitch()方法");
	}
	
	/**
	 * 更新本体中链路数据属性，以更新带宽、时延 、丢包率为例
	 * @param json 更新的JSON字符串，包含了更新的信息
	 */
	public void updateLinkData(String json) {
		logger.info("更新本体中链路数据属性的方法");
		// 获取链路类
		OntClass cls = model.getOntClass(Constant.LINK_CLASS_NAMESPACES);
		// 获取要更新的属性
		Property band = model.getProperty(Constant.SDN_NAMESPACES + "bandwidth");
		Property ip = model.getProperty(Constant.SDN_NAMESPACES + "latency");
		Property cs = model.getProperty(Constant.SDN_NAMESPACES + "drop_rate");
		// 获取标记链路的属性
		Property src = model.getProperty(Constant.SDN_NAMESPACES + "src_swid");
		Property port = model.getProperty(Constant.SDN_NAMESPACES + "src_port");
		// 获取链路类的所有实例
		ExtendedIterator<? extends OntResource>	instances = cls.listInstances();
		// 将实例存储到map中，便于json字符串过来查找更新
		Map<String, Individual> map = new HashMap<>();
		while(instances.hasNext()) {
			Individual temp = (Individual) instances.next();
			String srcSw = temp.getPropertyValue(src).toString();
			String srcPort = temp.getPropertyValue(port).toString();
			map.put(srcSw + srcPort, temp);
		}
		// 解析收到的JSON字符串，并持久化到本体
		JSONArray jsonArr = JSONArray.parseArray(json);
		for(int i = 0; i < jsonArr.size(); i++) {
			JSONObject obj = jsonArr.getJSONObject(i);
			String key = obj.getString("link");
			if(map.containsKey(key)) {
				Individual indi = map.get(key);//obj.getString("dropRate")
				// 不能直接将从json字符串中得到的值，在本体中修改，需要先包装为RDFNode
				RDFNode bandwidth = model.createLiteral(obj.getString("bandwidth"));
				RDFNode latency = model.createLiteral(obj.getString("latency"));
				RDFNode dropRate = model.createLiteral(String.valueOf(obj.getDouble("dropRate")));
				indi.setPropertyValue(band, bandwidth);
				indi.setPropertyValue(ip, latency);
				indi.setPropertyValue(cs, dropRate);
			}
		}
		logger.info("持久化到本体");
		writeIntoOntology();
	}
	
	
	
}
