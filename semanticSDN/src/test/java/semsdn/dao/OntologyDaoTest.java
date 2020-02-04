package semsdn.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import semsdn.devices.Host;
import semsdn.devices.Link;
import semsdn.devices.Switch;

/**
 * 单元测试类：主要对类中的基本函数进行测试，防止此处出错造成大量的工作
 * @author ssj
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OntologyDaoTest {

	private static final Logger logger = LoggerFactory.getLogger(OntologyDaoTest.class);
	
	@Autowired
	OntologyDao ontologyDao;
	
	// public List<Host> getAllHosts()
	@Test
	public void testGetAllHosts() {
		List<Host> hosts = ontologyDao.getAllHosts();
		for(Host host : hosts) {
			logger.info(host.toString());
		}
	}
	
	// public List<Switch> getAllSwitches()
	@Test
	public void testGetAllSwitches() {
		List<Switch> switches = ontologyDao.getAllSwitches();
		for(Switch sw : switches) {
			logger.info(sw.toString());
		}
	}
	
	// public List<Link> getAllLinks()
	@Test
	public void testGetAllLinks() {
		List<Link> links = ontologyDao.getAllLinks();
		for(Link link : links) {
			logger.info(link.toString());
		}
	}
	
	// public String getResourceInfo(String sparql)
	@Test
	public void testGetResourceInfo() {
		String sparql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
				"PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n" + 
				"SELECT ?subject ?object\r\n" + 
				"	WHERE { ?subject rdfs:subClassOf ?object }";
		logger.info(ontologyDao.getResourceInfo(sparql));
	}
	
}
