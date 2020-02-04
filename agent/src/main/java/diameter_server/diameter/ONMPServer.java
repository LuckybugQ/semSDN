package diameter_server.diameter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import diameter_server.constant.URLConstant;
import diameter_server.httputils.HttpUtils;
import dk.i1.diameter.AVP;
import dk.i1.diameter.AVP_Integer32;
import dk.i1.diameter.AVP_UTF8String;
import dk.i1.diameter.InvalidAVPLengthException;
import dk.i1.diameter.Message;
import dk.i1.diameter.ProtocolConstants;
import dk.i1.diameter.Utils;
import dk.i1.diameter.node.Capability;
import dk.i1.diameter.node.ConnectionKey;
import dk.i1.diameter.node.InvalidSettingException;
import dk.i1.diameter.node.NodeManager;
import dk.i1.diameter.node.NodeSettings;
import dk.i1.diameter.node.Peer;

public class ONMPServer extends NodeManager {

	private static Logger logger = LoggerFactory.getLogger(ONMPServer.class);
	
	// ������IP��ַ
	private static String host_id;
	private static String realm = "exchange.info";
	// �����Ķ˿�
	static int port = 3868;
	static NodeSettings node_settings;
	HttpUtils httpUtils = new HttpUtils();
	public DataCollector dataCollector;
	
	public ONMPServer(NodeSettings node_settings) {
		super(node_settings);
	}

	/**
	 * �õ�serverʵ��
	 * @return
	 */
	public static ONMPServer getInstance() {
		Capability capability = new Capability();
		capability.addAuthApp(ProtocolConstants.DIAMETER_APPLICATION_CREDIT_CONTROL);
		try {
			logger.info("���ýڵ㿪ʼnode_settings");
			node_settings = new NodeSettings(host_id, realm, 666, capability, port, "diameter-server", 0x01000000);
		} catch (InvalidSettingException e) {
			e.printStackTrace();
		}
		logger.info("���ýڵ����");
		logger.info("��ʼ����ONMPЭ�����ˣ�");
		ONMPServer server = new ONMPServer(node_settings);
		return server;
	}

	public static void main(String[] args) throws Exception {
		System.out.println("������IP��ַ" + args[0]);
		if(args.length != 0) {
			host_id = args[0];
//			URLConstant.HOST_IP_ADDRESS = args[0];
		}
		
		//System.out.println(host_id);
		ONMPServer server = ONMPServer.getInstance();
		server.dataCollector = new FloodlightDataCollector();
		server.start();
		logger.info("����ONMPЭ��������ɣ�");
		System.out.println("��Enter����ֹONMPЭ�����ˣ�");
		System.in.read();
		server.stop(50);
	}

	protected void handleRequest(dk.i1.diameter.Message request, ConnectionKey connkey, Peer peer) {
		logger.info("enter handleRequest(), ��ʼ��������.");
		logger.info("�������н������ݣ�");
		AVP avp_req = request.find(ProtocolConstants.DI_NM_REQ);
		AVP_Integer32 avp_con = null;
		int avp_con_value = 0;
		if(avp_req != null) {
			try {
				avp_con = new AVP_Integer32(avp_req);
				avp_con_value = avp_con.queryValue();
				logger.info("���յ�����Ϣ��" + avp_con_value);
			} catch (InvalidAVPLengthException e) {
				e.printStackTrace();
			}
		}
		
		
		String ans = null;
		switch(avp_con_value) {
			case ProtocolConstants.DI_SEMNM_SWITCHES :
				ans = dataCollector.getSwitches();
				break;
			case ProtocolConstants.DI_SEMNM_LINK :
				ans = dataCollector.getLinks();
				break;
			case ProtocolConstants.DI_SEMNM_HOST :
				ans = dataCollector.getHosts();
				break;
			case ProtocolConstants.DI_SEMNM_LINK_PER :
				AVP_UTF8String linkInfo = new AVP_UTF8String(request.find(ProtocolConstants.DI_SEMNM_LINK_PER));
				String strs = linkInfo.queryValue();
				ans = dataCollector.getLinkPerformance(strs);
				break;
			case ProtocolConstants.DI_SEMNM_PORT_PER :
				AVP_UTF8String swport = new AVP_UTF8String(request.find(ProtocolConstants.DI_SEMNM_PORT_PER));
				String str = swport.queryValue();
				ans = dataCollector.getPortformance(str);
				break;
			case ProtocolConstants.ONMP_LINK :
				ans = dataCollector.getLinkForOntology();
				break;
		}
		
		logger.info("����ظ���Ϣ��");
		Message answer = new Message();
		answer.prepareResponse(request);
		AVP avp = request.find(ProtocolConstants.DI_SESSION_ID);
		if (avp != null) answer.add(avp);
		node().addOurHostAndRealm(answer);
		
		answer.add(new AVP_UTF8String(ProtocolConstants.DI_NM_RESP,ans));
		
		Utils.setMandatory_RFC3588(answer);
		logger.info("����ظ���Ϣ��ɣ�");
		try {
			answer(answer, connkey);
		} catch (dk.i1.diameter.node.NotAnAnswerException ex) {
			ex.printStackTrace();
		}
	}

}