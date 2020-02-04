package semsdn.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dk.i1.diameter.ProtocolConstants;
import semsdn.dao.OntologyDao;
import semsdn.dao.UpdateDao;
import semsdn.diameter.ONMPClient;


/**
 * 该类用于构建拓扑，由于Floodlight中主机获取的不稳定性，因此，先要通过手动运行此函数，使得本体中的拓扑构建正常
 * 可以在启动floodlight之后在mininet中pingall，此时主机将完全发现
 * @author ssj
 *
 */
@Service
public class TopologyService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	OntologyDao ontologyDao = new OntologyDao();
	UpdateDao owlDao = new UpdateDao();
	
	// 单元函数，仅需使用鼠标选中干函数，邮件选择run as ,junit test
	public void constructTopology() {
		// 删除所有实例
		owlDao.deleteIndividuals();
		// 获取交换机信息
		String switches = ONMPClient.sendDiameterReq(ProtocolConstants.DI_SEMNM_SWITCHES);
		logger.info("=====" + switches);
		// 创建交换机实例
		owlDao.createIndividualForSwitch(switches);
		// 获取主机实例
		String hosts = ONMPClient.sendDiameterReq(ProtocolConstants.DI_SEMNM_HOST);
		logger.info(hosts);
		if(!hosts.equals("NO MODIFY")) {
			// 创建主机实例
			owlDao.createIndividualForHost(hosts);
		}
		// 获取链路信息
		String links = ONMPClient.sendDiameterReq(ProtocolConstants.DI_SEMNM_LINK);
		logger.info(links);
		// 创建链路实例
		owlDao.createIndividualForLinkAndPort(links);
	}
	
	
	
}
