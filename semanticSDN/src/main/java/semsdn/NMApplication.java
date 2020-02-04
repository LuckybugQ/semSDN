package semsdn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import semsdn.constant.Constant;

@SpringBootApplication
public class NMApplication {
	
	
	// 网管应用程序执行的入口,运行该主函数启动整个应用
	public static void main(String[] args) {
		if(args.length != 0) {
			Constant.DESTHOST_IP_ADDRESS = args[0];
			Constant.SOURCE_IP_ADDRESS = args[1];
		}
		SpringApplication.run(NMApplication.class, args);
	}
	
}
