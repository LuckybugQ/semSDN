package semsdn.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateDaoTest {

	private static final Logger logger = LoggerFactory.getLogger(UpdateDaoTest.class);
	
	@Autowired
	UpdateDao updateDao;
	
	@Test
	public void testGetAllHosts() {
		updateDao.updateLinkData("");
	}
	
}
