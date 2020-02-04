package semsdn.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import semsdn.dao.OntologyDaoTest;
import semsdn.devices.Host;
import semsdn.devices.Link;
import semsdn.devices.Switch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InfoServiceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(OntologyDaoTest.class);

	@Autowired
	InfoService infoService;
	
	// public List<Host> getAllHosts()
	@Test
	public void testGetAllHosts() {
		List<Host> hosts = infoService.getAllHosts();
		for(Host host : hosts) {
			logger.info(host.toString());
		}
	}
	
	// public List<Switch> getAllSwitches()
	@Test
	public void testGetAllSwitches() {
		List<Switch> switches = infoService.getAllSwitches();
		for(Switch sw : switches) {
			logger.info(sw.toString());
		}
	}
	
	// public List<Link> getAllLinks()
	@Test
	public void testGetAllLinks() {
		List<Link> links = infoService.getAllLinks();
		for(Link link : links) {
			logger.info(link.toString());
		}
	}
	
	
}
