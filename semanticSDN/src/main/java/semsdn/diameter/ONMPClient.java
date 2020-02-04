package semsdn.diameter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.i1.diameter.AVP;
import dk.i1.diameter.AVP_Integer32;
import dk.i1.diameter.AVP_UTF8String;
import dk.i1.diameter.AVP_Unsigned32;
import dk.i1.diameter.Message;
import dk.i1.diameter.ProtocolConstants;
import dk.i1.diameter.Utils;
import dk.i1.diameter.node.Capability;
import dk.i1.diameter.node.EmptyHostNameException;
import dk.i1.diameter.node.InvalidSettingException;
import dk.i1.diameter.node.NodeManager;
import dk.i1.diameter.node.NodeSettings;
import dk.i1.diameter.node.Peer;
import dk.i1.diameter.node.SimpleSyncClient;
import dk.i1.diameter.node.UnsupportedTransportProtocolException;
import semsdn.constant.Constant;

/**
 * ONMP协议的客户端，完成向服务器端的消息请求工作
 * @author ssj
 *
 */
public class ONMPClient extends NodeManager {
	
	
	private static Logger logger = LoggerFactory.getLogger(ONMPClient.class);
	
	private static int our_vendor_id = 666;
	// 本机的IP地址，部署在不同的主机中需要修改为对应的IP地址
	private static String host_id = Constant.SOURCE_IP_ADDRESS;
	private static String realm = "exchange.info";
	

	public ONMPClient(NodeSettings settings) {
		super(settings);
	}
	
	// 客户端启动前的配置
	public static NodeSettings config(String host_id) {
		Capability capability = new Capability();
		capability.addAuthApp(ProtocolConstants.DIAMETER_APPLICATION_CREDIT_CONTROL);
		NodeSettings nodeSettings = null;
		try {
			nodeSettings = 
					new NodeSettings(host_id, realm, our_vendor_id, capability, 0, "diameter-client", 0x01000000);
		} catch (InvalidSettingException e) {
			logger.error(e.getMessage(), e);
		}
		return nodeSettings;
	}

	
	/**
	 * 某一台具体设备的消息请求函数
	 * @return
	 * @throws EmptyHostNameException 
	 */
	public static String sendDiameterReqForPerformance(int messageType, String which) {
		logger.info("enter sendDiameterReqForSwitch()函数,发送请求");
		logger.info("发送请求前的配置");
		Peer[] peers = null;
		try {
			peers = new Peer[] { new Peer(Constant.DESTHOST_IP_ADDRESS, Constant.DESTHOST_PORT) };
		} catch (EmptyHostNameException e1) {
			logger.error(e1.getMessage(), e1);
		}
		Capability capability = new Capability();
		capability.addAuthApp(ProtocolConstants.DIAMETER_APPLICATION_CREDIT_CONTROL);
		NodeSettings nodeSettings = null;
		try {
			nodeSettings = 
					new NodeSettings(host_id, realm, our_vendor_id, capability, 0, "diameter-client", 0x01000000);
		} catch (InvalidSettingException e) {
			logger.error(e.getMessage(), e);
		}
		
		logger.info("客户端！");
		SimpleSyncClient ssc = new SimpleSyncClient(nodeSettings, peers);
		try {
			ssc.start();
			ssc.waitForConnection();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (UnsupportedTransportProtocolException e) {
			logger.error(e.getMessage(), e);
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("构造请求！");
		Message request = new Message();
		request.hdr.command_code = ProtocolConstants.DIAMETER_COMMAND_CC;
		request.hdr.application_id = ProtocolConstants.DIAMETER_APPLICATION_CREDIT_CONTROL;
		request.hdr.setRequest(true);
		request.hdr.setProxiable(true);
		request.add(new AVP_UTF8String(ProtocolConstants.DI_SESSION_ID, ssc.node().makeNewSessionId()));
		ssc.node().addOurHostAndRealm(request);	
		request.add(new AVP_Unsigned32(ProtocolConstants.DI_AUTH_APPLICATION_ID,
				ProtocolConstants.DIAMETER_APPLICATION_CREDIT_CONTROL));
		
		logger.info("在请求中添加内容！");
		request.add(new AVP_Integer32(ProtocolConstants.DI_NM_REQ, messageType));
		request.add(new AVP_UTF8String(messageType, which));
		logger.info("构造请求完成！");
		
		Utils.setMandatory_RFC3588(request);
		Utils.setMandatory_RFC4006(request);
		
		logger.info("发送消息 并接收响应消息！");
		Message response = ssc.sendRequest(request);
		
		String res = null;
		if (response == null) {
			logger.info("No response!");
		} else {
			logger.info("解析并返回响应消息！");
			AVP result = response.find(ProtocolConstants.DI_NM_RESP);
			if (result != null) {
				AVP_UTF8String result_string = new AVP_UTF8String(result);
				res = result_string.queryValue();
			}
		}
		ssc.stop();
		logger.info("outer sendDiameterReqForSwitch()函数");
		return res;
	}
	
	
	/**
	 * 根据类型请求信息
	 * messageType ProtocolConstants类中编的数字
	 * @return
	 * @throws EmptyHostNameException 
	 */
	public static String sendDiameterReq(int messageType) {
		logger.info("enter sendDiameterReqForSwitch()函数,发送请求");

		logger.info("发送请求前的配置！");
		Peer[] peers = null;
		try {
			peers = new Peer[] { new Peer(Constant.DESTHOST_IP_ADDRESS, Constant.DESTHOST_PORT) };
		} catch (EmptyHostNameException e1) {
			logger.error(e1.getMessage(), e1);
		}
		Capability capability = new Capability();
		capability.addAuthApp(ProtocolConstants.DIAMETER_APPLICATION_CREDIT_CONTROL);
		NodeSettings nodeSettings = null;
		try {
			nodeSettings = 
					new NodeSettings(host_id, realm, our_vendor_id, capability, 0, "diameter-client", 0x01000000);
		} catch (InvalidSettingException e) {
			logger.error(e.getMessage(), e);
		}
		
		SimpleSyncClient ssc = new SimpleSyncClient(nodeSettings, peers);
		try {
			ssc.start();
			ssc.waitForConnection();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (UnsupportedTransportProtocolException e) {
			logger.error(e.getMessage(), e);
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		}
		
		Message request = new Message();
		request.hdr.command_code = ProtocolConstants.DIAMETER_COMMAND_CC;
		request.hdr.application_id = ProtocolConstants.DIAMETER_APPLICATION_CREDIT_CONTROL;
		request.hdr.setRequest(true);
		request.hdr.setProxiable(true);
		request.add(new AVP_UTF8String(ProtocolConstants.DI_SESSION_ID, ssc.node().makeNewSessionId()));
		ssc.node().addOurHostAndRealm(request);	
		request.add(new AVP_Unsigned32(ProtocolConstants.DI_AUTH_APPLICATION_ID,
				ProtocolConstants.DIAMETER_APPLICATION_CREDIT_CONTROL));
		
		request.add(new AVP_Integer32(ProtocolConstants.DI_NM_REQ, messageType));
		
		Utils.setMandatory_RFC3588(request);
		Utils.setMandatory_RFC4006(request);
		
		Message response = ssc.sendRequest(request);
		
		String res = null;
		if (response == null) {
			logger.info("No response!");
		} else {
			logger.info("等待回复报文");
			AVP result = response.find(ProtocolConstants.DI_NM_RESP);
			if (result != null) {
				AVP_UTF8String result_string = new AVP_UTF8String(result);
				res = result_string.queryValue();
			}
		}
		ssc.stop();
		logger.info("outer sendDiameterReqForSwitch()方法");
		return res;
	}
	

	
	
}
