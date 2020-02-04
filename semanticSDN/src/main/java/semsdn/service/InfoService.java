package semsdn.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.i1.diameter.ProtocolConstants;
import semsdn.dao.OntologyDao;
import semsdn.devices.Host;
import semsdn.devices.Link;
import semsdn.devices.Switch;
import semsdn.diameter.ONMPClient;

/**
 * 服务层：主要使用Dao包中的基本类组合新的服务功能
 * @author ssj
 *
 */
@Service
public class InfoService {

	@Autowired
	OntologyDao ontologyDao;
	
	/**
	 * 获取所有的主机
	 * @return
	 */
	public List<Host> getAllHosts() {
		return ontologyDao.getAllHosts();
	}

	/**
	 * 获取所有的交换机
	 * @return
	 */
	public List<Switch> getAllSwitches() {
		return ontologyDao.getAllSwitches();
	}
	
	/**
	 * 获取所有的链路
	 * @return
	 */
	public List<Link> getAllLinks() {
		return ontologyDao.getAllLinks();
	}
	
	
	/**
	 * 获取某个交换机的端口列表
	 * @return
	 */
	public Map<String, List<Integer>> getAllSwWithPort() {
		Map<String, List<Integer>> res = ontologyDao.getAllSwWithPort();
		return res;
	}

	/**
	 * 获取交换机端口的性能参数
	 * @param switchId
	 * @param portId
	 * @return
	 */
	public String getPortPerformance(String switchId, String portId) {
		String res = ONMPClient.sendDiameterReqForPerformance(ProtocolConstants.DI_SEMNM_PORT_PER, switchId + "/" +  portId);
		while(res == null || res.length() <= 0) {
			res = ONMPClient.sendDiameterReqForPerformance(ProtocolConstants.DI_SEMNM_PORT_PER, switchId + "/" +  portId);
		}
		return res;
	}

	/**
	 * 获取交换机链路的性能参数
	 * @param switchId
	 * @param portId
	 * @return
	 */
	public String getLinkPerformance(String switchId, int portId) {
		String res = 
				ONMPClient.sendDiameterReqForPerformance(ProtocolConstants.DI_SEMNM_LINK_PER, switchId + "/" +  portId);
		while(res == null || res.length() <= 0) {
			res = ONMPClient.sendDiameterReqForPerformance(ProtocolConstants.DI_SEMNM_LINK_PER, switchId + "/" +  portId);
		}
		return res;
	}
	
	public String getResourceInfo(String sparql) {
		return ontologyDao.getResourceInfo(sparql);
	}

}
