package semsdn.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import semsdn.devices.Host;
import semsdn.devices.Link;
import semsdn.devices.Switch;
import semsdn.service.InfoService;

/**
 * 拦截前端请求的类
 * @author ssj
 *
 */
@Controller // 此处必须加注解@Controller，用于接受URL请求
public class MyController {
	
	@Autowired  // 此处是通过注解注入，防止了new对象的耦合，以及对象的冗余
	InfoService infoService;
	
	private static Logger logger = LoggerFactory.getLogger(MyController.class);
	
	//------------页面的定向start---------------

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indexLink() {
		return "index";
	}
	
	
	@RequestMapping(value = "/network-topology", method = RequestMethod.GET)
	public String topology() {
		return "network-topology";
	}
	
	@RequestMapping(value = "/switch", method = RequestMethod.GET)
	public String switchLink() {
		return "switch";
	}
	
	@RequestMapping(value = "/host", method = RequestMethod.GET)
	public String hostLink() {
		return "host";
	}
	
	@RequestMapping(value = "/link", method = RequestMethod.GET)
	public String linkLink() {
		return "link";
	}
	
	@RequestMapping(value = "/link-performance", method = RequestMethod.GET)
	public String linkPerLink() {
		return "link-performance";
	}
	
	@RequestMapping(value = "/port-performance", method = RequestMethod.GET)
	public String portPerLink() {
		return "port-performance";
	}
	
	@RequestMapping(value = "/sdn-controller", method = RequestMethod.GET)
	public String controllerLink() {
		return "sdn-controller";
	}
	
	@RequestMapping(value = "/sparql", method = RequestMethod.GET)
	public String sparql() {
		return "sparql";
	}
	//------------页面的定向end---------------
	
	
	//------------主机页面start--------------
	/**
	 * 主机信息列表获取的URL，host.html
	 * @param page 第几页
	 * @param limit 每页多少条记录
	 * @return
	 */
	// ?page=1&limit=20 传回的两个数据
	@ResponseBody
	@RequestMapping(value = "/hostInfo", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String hostList(@RequestParam int page, @RequestParam int limit) {
		List<Host> hosts = infoService.getAllHosts();
		int total = hosts.size();
		JSONArray array = null;
		if(page == 1) {
			if(limit > hosts.size()) {
				array = JSONArray.parseArray(JSON.toJSONString(hosts.subList(0, hosts.size() - 1)));
			} else {
				array = JSONArray.parseArray(JSON.toJSONString(hosts.subList(0, limit - 1)));
			}
		} else {
			int left = limit * (page - 1);
			int right = limit * page;
			if (right > hosts.size() && left <= hosts.size()) {
				array = JSONArray.parseArray(JSON.toJSONString(hosts.subList(left - 1, hosts.size() - 1)));
			} else if(right <= hosts.size() && left <= hosts.size()) {
				array = JSONArray.parseArray(JSON.toJSONString(hosts.subList(left - 1, right - 1)));
			}
		}
		JSONObject root = new JSONObject();
		if(array.size() == 0) {
			root.put("errorCode", -1);
			root.put("message", "数据获取为空，已无数据");
		} else {
			root.put("errorCode", 0);
			root.put("message", "正常");
		}
		JSONObject data = new JSONObject();
		data.put("lists", array);
		data.put("page", page);
		data.put("total", total);
		data.put("limit", limit);
		root.put("data", data);
		return root.toString();
	}
	//------------主机页面end--------------
	
	
	//------------交换机页面start--------------
	/**
	 * 交换机信息列表获取的URL，switch.html
	 * @param page 第几页
	 * @param limit 每页多少条记录
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/switcheInfo", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String switchList(@RequestParam int page, @RequestParam int limit) {
		List<Switch> hosts = infoService.getAllSwitches();
		int total = hosts.size();
		JSONArray array = null;
		if(page == 1) {
			if(limit > hosts.size()) {
				array = JSONArray.parseArray(JSON.toJSONString(hosts.subList(0, hosts.size() - 1)));
			} else {
				array = JSONArray.parseArray(JSON.toJSONString(hosts.subList(0, limit - 1)));
			}
		} else {
			int left = limit * (page - 1);
			int right = limit * page;
			if (right > hosts.size() && left <= hosts.size()) {
				array = JSONArray.parseArray(JSON.toJSONString(hosts.subList(left - 1, hosts.size() - 1)));
			} else if(right <= hosts.size() && left <= hosts.size()) {
				array = JSONArray.parseArray(JSON.toJSONString(hosts.subList(left - 1, right - 1)));
			}
		}
		JSONObject root = new JSONObject();
		if(array.size() == 0) {
			root.put("errorCode", -1);
			root.put("message", "数据获取为空，已无数据");
		} else {
			root.put("errorCode", 0);
			root.put("message", "正常");
		}
		JSONObject data = new JSONObject();
		data.put("lists", array);
		data.put("page", page);
		data.put("total", total);
		data.put("limit", limit);
		root.put("data", data);
		return root.toString();
	}
	
	//------------交换机页面end--------------
	
	
	//------------链路信息页面start--------------
	/**
	 * 获取链路信息的URL，link.html
	 * @param page 第几页
	 * @param limit 每页多少条记录
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/linkInfo", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String linkList(@RequestParam int page, @RequestParam int limit) {
		List<Link> hosts = infoService.getAllLinks();
		System.out.println(hosts);
		int total = hosts.size();
		JSONArray array = null;
		if(page == 1) {
			if(limit > hosts.size()) {
				array = JSONArray.parseArray(JSON.toJSONString(hosts.subList(0, hosts.size() - 1)));
			} else {
				array = JSONArray.parseArray(JSON.toJSONString(hosts.subList(0, limit - 1)));
			}
		} else {
			int left = limit * (page - 1);
			int right = limit * page;
			if (right > hosts.size() && left <= hosts.size()) {
				array = JSONArray.parseArray(JSON.toJSONString(hosts.subList(left - 1, hosts.size() - 1)));
			} else if(right <= hosts.size() && left <= hosts.size()) {
				array = JSONArray.parseArray(JSON.toJSONString(hosts.subList(left - 1, right - 1)));
			}
		}
		JSONObject root = new JSONObject();
		if(array.size() == 0) {
			root.put("errorCode", -1);
			root.put("message", "数据获取为空，已无数据");
		} else {
			root.put("errorCode", 0);
			root.put("message", "正常");
		}
		JSONObject data = new JSONObject();
		data.put("lists", array);
		data.put("page", page);
		data.put("total", total);
		data.put("limit", limit);
		root.put("data", data);
		return root.toString();
	}
	//------------交换机页面end--------------
	
	
	//------------拓扑显示页面start--------------
	/**
	 * 获取拓扑信息,显示拓扑
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/semSDN/network-topology", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String topologyInfo() {
		List<Host> hosts = infoService.getAllHosts();
		List<Switch> switches = infoService.getAllSwitches();
		List<Link> links = infoService.getAllLinks();
		JSONObject topologyData = new JSONObject();
		topologyData.put("errorCode", 0);
		topologyData.put("message", "正常");
		JSONArray hostArr = JSONArray.parseArray(JSON.toJSONString(hosts));
		JSONArray switchArr = JSONArray.parseArray(JSON.toJSONString(switches));
		JSONArray linkArr = JSONArray.parseArray(JSON.toJSONString(links));
		JSONObject data = new JSONObject();
		data.put("hosts", hostArr);
		data.put("switches", switchArr);
		data.put("links", linkArr);
		topologyData.put("data", data);
		return topologyData.toString();
	}
	
	//------------拓扑显示页面end--------------
	
	
	//------------端口性能显示页面start--------------
	@ResponseBody
	@RequestMapping(value = "/semSDN/port-performance", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String portPerformanceInfo(@RequestParam String mac, @RequestParam String port) {
		String portInfo = infoService.getPortPerformance(mac, port);
		JSONObject obj = JSONObject.parseObject(portInfo);
		JSONObject portPerformanceData = new JSONObject();
		portPerformanceData.put("errorCode", 0);
		portPerformanceData.put("message", "正常");
		JSONObject data = new JSONObject();
		data.put("bitsPerSecondRx", obj.get("bitsPerSecondRx"));
		data.put("bitsPerSecondTx", obj.get("bitsPerSecondTx"));
		data.put("rxBytes", obj.get("rxBytes"));
		data.put("txBytes", obj.get("txBytes"));
		data.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		logger.info(data.toString());
		portPerformanceData.put("data", data);
		return portPerformanceData.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/semSDN/switch/portlist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String switchPortList() {
		Map<String, List<Integer>> res = infoService.getAllSwWithPort();
		System.out.println(res.toString());
		JSONObject SwitchPortListData = new JSONObject();
		SwitchPortListData.put("errorCode", 0);
		SwitchPortListData.put("message", "����");
		
		JSONArray data = new JSONArray();
		for (String str : res.keySet()) {
			JSONObject obj = new JSONObject();
			obj.put("swid", str);
			obj.put("spid", JSONArray.parseArray(JSON.toJSONString(res.get(str))));
			data.add(obj);
		}
		SwitchPortListData.put("data", data);
		System.out.println(SwitchPortListData.toString());
		return SwitchPortListData.toString();
	}
	//------------端口性能显示页面end--------------
	
	//------------链路性能显示页面start--------------
	@ResponseBody
	@RequestMapping(value = "/semSDN/link-performance", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String linkPerformanceInfo(@RequestParam String link) {
		String[] strs = link.split(",");
		String linkPerformanceInfo = 
				infoService.getLinkPerformance(strs[0], Integer.valueOf(strs[1]));
		JSONObject obj = JSONObject.parseObject(linkPerformanceInfo);
		JSONObject linkPerformanceData = new JSONObject();
		linkPerformanceData.put("errorCode", 0);
		linkPerformanceData.put("message", "正常");
		
		JSONObject data = new JSONObject();
		data.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		data.put("latency", obj.get("latency"));
		data.put("dropRate", obj.get("dropRate"));
		data.put("bandwidthUtil", obj.get("bandwidthUtil"));
		linkPerformanceData.put("data", data);
		return linkPerformanceData.toString();
	}
	
	
	/**
	 * link-performance.html页面中选择要监控那条链路的列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/semSDN/linkList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String linkList() {
		List<Link> links = infoService.getAllLinks();
		List<String> linkdata = new ArrayList<>();
		JSONObject linkListData = new JSONObject();
		linkListData.put("errorCode", 0);
		linkListData.put("message", "正常");
		
		for(Link li : links) {
			StringBuilder sb = new StringBuilder();
			sb.append(li.getSrc_sid());
			sb.append(",");
			sb.append(li.getSrc_pid());
			linkdata.add(sb.toString());
		}
		JSONArray arr = JSONArray.parseArray(JSON.toJSONString(linkdata));
		linkListData.put("data", arr);
		return linkListData.toString();
	}
	
	//------------链路性能显示页面end--------------
	
	//------------SPARQL查询接口start--------------
	@ResponseBody
	@RequestMapping(value = "/sparqlInfo", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String sparqlHandleInfo(@RequestParam String sparql) {
		//System.out.println(sparql);
		return infoService.getResourceInfo(sparql);
	}
	//------------SPARQL查询接口end--------------

}
