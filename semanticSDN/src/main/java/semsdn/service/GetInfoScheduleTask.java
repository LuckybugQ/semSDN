package semsdn.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import dk.i1.diameter.ProtocolConstants;
import semsdn.dao.UpdateDao;
import semsdn.diameter.ONMPClient;

/**
 * 采用多线程定周期执行数据采集的任务，周期性的修改本体中的信息
 * @author ssj
 *
 */
@Service  // 注入Bean
@EnableScheduling // 开启定时任务的注解
@EnableAsync  // 开启多线程模式
public class GetInfoScheduleTask {
	
	private static Logger logger = LoggerFactory.getLogger(GetInfoScheduleTask.class);
	
	@Autowired
	UpdateDao updateDao;
	
	@Async
	@Scheduled(fixedDelay = 1000 * 10)  // 设定收集的间隔，1000ms*10=10s
	public void getLinkDataTask() {
		logger.info("collector link data begin....");
		String res = ONMPClient.sendDiameterReq(ProtocolConstants.ONMP_LINK);
		while(res == null || res.length() <= 0) {
			res = ONMPClient.sendDiameterReq(ProtocolConstants.ONMP_LINK);
		}
		System.out.println(updateDao);
		updateDao.updateLinkData(res);
	}
	
	/**
	 * 如果需要更多的任务，仅仅需要再写个方法，方法上加上@Async和@Scheduled(fixedDelay = 2000)注解，应用将会自动周期执行任务
	 * @throws InterruptedException
	 */
	// 如下就是另一个任务的开启，注意函数无需返回值
/*	@Async
	@Scheduled(fixedDelay = 2000)
	public void firstTask() throws InterruptedException {
		System.out.println("第一个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
        System.out.println();
	}
*/
	
}
