package diameter_server.httputils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtilsTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// ½»»»»úURL http://127.0.0.1:8080/wm/core/controller/switches/json
	// Á´Â·URL http://127.0.0.1:8080/wm/topology/links/json
	@Test
	public void getURLContentTest() {
		String str = 
				HttpUtils.getURLContent("http://127.0.0.1:8080/wm/core/controller/switches/json");
		System.out.println(str);
	}
	
}
