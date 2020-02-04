package diameter_server.diameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import diameter_server.httputils.Host;
import diameter_server.httputils.HttpUtils;


public class FloodlightDataCollector implements DataCollector {


	
	@Override
	public String getSwitches() {
		return HttpUtils.getURLContent("http://127.0.0.1:8080/wm/core/controller/switches/json");
	}

	@Override
	public String getLinks() {
		return HttpUtils.getURLContent("http://127.0.0.1:8080/wm/topology/links/json");
	}

	@Override
	public String getHosts() {
		return filterHostInfo(HttpUtils.getURLContent("http://127.0.0.1:8080/wm/device/"));
	}
	
	/**
	 * 过滤主机信息，不返回无效的信息
	 * @param result
	 * @return
	 */
	public String filterHostInfo(String result) {
		String jsonObject = JSONObject.parseObject(result).getString("devices").toString();
		JSONArray jsonArr = JSONArray.parseArray(jsonObject);
		List<Host> hostsInfo = new ArrayList<>();
		for(int i = 0; i < jsonArr.size(); i++) {
			JSONObject json = jsonArr.getJSONObject(i);
			String ipv4 = json.getString("ipv4");
			if(ipv4.length() > 2) {
				String mac = json.getString("mac");
				long lastSeen = json.getLong("lastSeen");
				String att = json.getString("attachmentPoint");
				JSONObject js = JSONObject.parseObject(att.substring(1, att.length() - 1));
				if(js != null) {
					String swid = js.getString("switch");
					int pid = js.getIntValue("port");
					hostsInfo.add(new Host(ipv4, mac, swid, pid, lastSeen));
				} else {
					return "NO MODIFY";
				}
			}
		}
		if(hostsInfo.size() <= 0) {
			return "NO MODIFY";
		}
		return JSON.toJSONString(hostsInfo);
	}

	@Override
	public String getLinkPerformance(String link) {
		// 需要去根据link去获取
		JSONObject obj = new JSONObject();
		Random random = new Random();
		obj.put("latency", random.nextInt(30) + 20);
		obj.put("dropRate", random.nextDouble());
		obj.put("bandwidthUtil", random.nextInt(20) + 20);
		return obj.toString();
	}

	@Override
	public String getPortformance(String switchAndPort) {
//		String[] temp = switchAndPort.split("/"); //收集的交换机以及端口解析出来
		JSONObject obj = new JSONObject();
		Random random = new Random();
		obj.put("bitsPerSecondRx", random.nextInt(11) + 20);
		obj.put("bitsPerSecondTx", random.nextInt(11) + 20);
		obj.put("rxBytes", random.nextInt(20) + 20);
		obj.put("txBytes", random.nextInt(20) + 20);
		return obj.toString();
	}

	@Override
	public String getLinkForOntology() {
		Random ran = new Random();
		JSONArray jsonArr = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("link", "00:00:00:00:00:00:00:073");
		obj.put("bandwidth", ran.nextInt(20) + 20);
		obj.put("latency", ran.nextInt(30) + 20);
		obj.put("dropDate", ran.nextDouble());
		jsonArr.add(obj);
		JSONObject obj2 = new JSONObject();
		obj2.put("link", "00:00:00:00:00:00:00:074");
		obj2.put("bandwidth", ran.nextInt(20) + 20);
		obj2.put("latency", ran.nextInt(30) + 20);
		obj2.put("dropDate", ran.nextDouble());
		jsonArr.add(obj2);
		JSONObject obj3 = new JSONObject();
		obj3.put("link", "00:00:00:00:00:00:00:052");
		obj3.put("bandwidth", ran.nextInt(20) + 20);
		obj3.put("latency", ran.nextInt(30) + 20);
		obj3.put("dropDate", ran.nextDouble());
		jsonArr.add(obj3);
		return jsonArr.toString();
	}

	
		
}
