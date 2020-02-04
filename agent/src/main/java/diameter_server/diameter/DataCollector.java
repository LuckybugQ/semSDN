package diameter_server.diameter;

/**
 * 该类是从控制器中获取数据的接口，通常控制器中会提供rest api，
 * 在实现该类的时候可以通过HttpUtils类中的getURLContent()
 * 方法获取数据，需要注意的是需要在该函数中过滤结果，才能在前端页面以及
 * 本体中正常的显示与存储
 * 
 * @author ssj
 *
 */
public interface DataCollector {
	

	/**
	 * 返回数据的格式为
	 * [
		 {"inetAddress":"/192.168.50.113:51333","connectedSince":1555414292335,"openFlowVersion":"OF_13","switchDPID":"00:00:00:00:00:00:00:09"},
		 {"inetAddress":"/192.168.50.113:51335","connectedSince":1555414292335,"openFlowVersion":"OF_13","switchDPID":"00:00:00:00:00:00:00:03"},
		 {"inetAddress":"/192.168.50.113:51336","connectedSince":1555414292335,"openFlowVersion":"OF_13","switchDPID":"00:00:00:00:00:00:00:04"},
		 {"inetAddress":"/192.168.50.113:51332","connectedSince":1555414292335,"openFlowVersion":"OF_13","switchDPID":"00:00:00:00:00:00:00:08"},
		 {"inetAddress":"/192.168.50.113:51330","connectedSince":1555414292335,"openFlowVersion":"OF_13","switchDPID":"00:00:00:00:00:00:00:05"},
		 {"inetAddress":"/192.168.50.113:51338","connectedSince":1555414292335,"openFlowVersion":"OF_13","switchDPID":"00:00:00:00:00:00:00:06"},
		 {"inetAddress":"/192.168.50.113:51337","connectedSince":1555414292335,"openFlowVersion":"OF_13","switchDPID":"00:00:00:00:00:00:00:07"},
		 {"inetAddress":"/192.168.50.113:51334","connectedSince":1555414292335,"openFlowVersion":"OF_13","switchDPID":"00:00:00:00:00:00:00:01"},
		 {"inetAddress":"/192.168.50.113:51331","connectedSince":1555414292335,"openFlowVersion":"OF_13","switchDPID":"00:00:00:00:00:00:00:02"}
	   ]
	 * @return
	 */
	// 如果要想灵活的根据IP查询，此处可以加入形参String ip
	public String getSwitches();
	
	/**
	 * 返回数据的格式为
	 [
	 	{"src-switch":"00:00:00:00:00:00:00:02","src-port":2,"dst-switch":"00:00:00:00:00:00:00:03","dst-port":1,"type":"internal","direction":"bidirectional","latency":104},
	 	{"src-switch":"00:00:00:00:00:00:00:03","src-port":4,"dst-switch":"00:00:00:00:00:00:00:06","dst-port":1,"type":"internal","direction":"bidirectional","latency":57},
	 	{"src-switch":"00:00:00:00:00:00:00:05","src-port":3,"dst-switch":"00:00:00:00:00:00:00:07","dst-port":2,"type":"internal","direction":"bidirectional","latency":18},
	 	{"src-switch":"00:00:00:00:00:00:00:03","src-port":5,"dst-switch":"00:00:00:00:00:00:00:07","dst-port":1,"type":"internal","direction":"bidirectional","latency":57},
	 	{"src-switch":"00:00:00:00:00:00:00:03","src-port":3,"dst-switch":"00:00:00:00:00:00:00:08","dst-port":2,"type":"internal","direction":"bidirectional","latency":57},
	 	{"src-switch":"00:00:00:00:00:00:00:03","src-port":2,"dst-switch":"00:00:00:00:00:00:00:04","dst-port":1,"type":"internal","direction":"bidirectional","latency":51},
	 	{"src-switch":"00:00:00:00:00:00:00:04","src-port":2,"dst-switch":"00:00:00:00:00:00:00:08","dst-port":1,"type":"internal","direction":"bidirectional","latency":51},
	 	{"src-switch":"00:00:00:00:00:00:00:01","src-port":1,"dst-switch":"00:00:00:00:00:00:00:02","dst-port":1,"type":"internal","direction":"bidirectional","latency":32},
	 	{"src-switch":"00:00:00:00:00:00:00:07","src-port":3,"dst-switch":"00:00:00:00:00:00:00:09","dst-port":1,"type":"internal","direction":"bidirectional","latency":50},
	 	{"src-switch":"00:00:00:00:00:00:00:05","src-port":4,"dst-switch":"00:00:00:00:00:00:00:09","dst-port":2,"type":"internal","direction":"bidirectional","latency":1},
	 	{"src-switch":"00:00:00:00:00:00:00:05","src-port":2,"dst-switch":"00:00:00:00:00:00:00:06","dst-port":2,"type":"internal","direction":"bidirectional","latency":58},
	 	{"src-switch":"00:00:00:00:00:00:00:07","src-port":4,"dst-switch":"00:00:00:00:00:00:00:08","dst-port":3,"type":"internal","direction":"bidirectional","latency":56},
	 	{"src-switch":"00:00:00:00:00:00:00:02","src-port":3,"dst-switch":"00:00:00:00:00:00:00:05","dst-port":1,"type":"internal","direction":"bidirectional","latency":16}
	 ]
	 * @return
	 */
	public String getLinks();
	
	/**
	 * 返回数据的格式为
	 {
		"devices":[
		{"entityClass":"DefaultEntityClass","mac":["f6:23:e7:d9:c4:ac"],"ipv4":["10.0.0.24"],"ipv6":[],"vlan":["0x0"],"attachmentPoint":[{"switch":"00:00:00:00:00:00:00:09","port":"3"}],"lastSeen":1555415796323},
		{"entityClass":"DefaultEntityClass","mac":["f6:58:e9:7c:ff:5d"],"ipv4":["10.0.0.19"],"ipv6":[],"vlan":["0x0"],"attachmentPoint":[{"switch":"00:00:00:00:00:00:00:03","port":"6"}],"lastSeen":1555415797743},
		{"entityClass":"DefaultEntityClass","mac":["16:86:d0:98:b6:9b"],"ipv4":["10.0.0.28"],"ipv6":[],"vlan":["0x0"],"attachmentPoint":[{"switch":"00:00:00:00:00:00:00:06","port":"3"}],"lastSeen":1555415796615},
		{"entityClass":"DefaultEntityClass","mac":["92:6b:74:48:4c:e3"],"ipv4":["10.0.0.18"],"ipv6":[],"vlan":["0x0"],"attachmentPoint":[{"switch":"00:00:00:00:00:00:00:02","port":"5"}],"lastSeen":1555415797751},
		{"entityClass":"DefaultEntityClass","mac":["ce:fb:c5:f9:c3:be"],"ipv4":["10.0.0.27"],"ipv6":[],"vlan":["0x0"],"attachmentPoint":[{"switch":"00:00:00:00:00:00:00:05","port":"6"}],"lastSeen":1555415795509},
		{"entityClass":"DefaultEntityClass","mac":["86:a5:1c:7f:82:36"],"ipv4":["10.0.0.25"],"ipv6":[],"vlan":["0x0"],"attachmentPoint":[{"switch":"00:00:00:00:00:00:00:09","port":"4"}],"lastSeen":1555415795554},
		{"entityClass":"DefaultEntityClass","mac":["ee:d0:36:e9:12:50"],"ipv4":["10.0.0.23"],"ipv6":[],"vlan":["0x0"],"attachmentPoint":[{"switch":"00:00:00:00:00:00:00:07","port":"5"}],"lastSeen":1555415795759},
		{"entityClass":"DefaultEntityClass","mac":["82:4e:97:a4:f0:41"],"ipv4":["10.0.0.26"],"ipv6":[],"vlan":["0x0"],"attachmentPoint":[{"switch":"00:00:00:00:00:00:00:05","port":"5"}],"lastSeen":1555415795807},
		{"entityClass":"DefaultEntityClass","mac":["6e:d8:33:cc:ff:41"],"ipv4":["10.0.0.20"],"ipv6":[],"vlan":["0x0"],"attachmentPoint":[{"switch":"00:00:00:00:00:00:00:04","port":"3"}],"lastSeen":1555415795971},
		{"entityClass":"DefaultEntityClass","mac":["8a:2f:8f:79:5f:5a"],"ipv4":["10.0.0.16"],"ipv6":[],"vlan":["0x0"],"attachmentPoint":[{"switch":"00:00:00:00:00:00:00:01","port":"2"}],"lastSeen":1555415796062},
		{"entityClass":"DefaultEntityClass","mac":["0e:80:f3:1a:84:68"],"ipv4":["10.0.0.17"],"ipv6":[],"vlan":["0x0"],"attachmentPoint":[{"switch":"00:00:00:00:00:00:00:02","port":"4"}],"lastSeen":1555415796101},
		{"entityClass":"DefaultEntityClass","mac":["92:31:6d:89:c6:4a"],"ipv4":["10.0.0.21"],"ipv6":[],"vlan":["0x0"],"attachmentPoint":[{"switch":"00:00:00:00:00:00:00:04","port":"4"}],"lastSeen":1555415796220},
		{"entityClass":"DefaultEntityClass","mac":["9e:fb:07:3d:08:5b"],"ipv4":["10.0.0.22"],"ipv6":[],"vlan":["0x0"],"attachmentPoint":[{"switch":"00:00:00:00:00:00:00:08","port":"4"}],"lastSeen":1555415796361}]
     }
	 * @return
	 */
	public String getHosts();
	
	/**
	 * 
	 * @return
	 */
	public String getLinkPerformance(String link);
	
	/**
	 * 
	 * @return
	 */
	public String getPortformance(String switchAndPort);
	
	public String getLinkForOntology();

}
