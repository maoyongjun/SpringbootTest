package org.foxconn.springbootStart.util;

import java.io.File;
import java.io.IOException;

public class TestUtil {
//	public static void main(String[] args) {
//		FeachTaskUtil task = new FeachTaskUtil();
//		
//		--访问控制
//		Application a= new Application();
//		a.
//		解耦
//		a
//		SpringBootServletInitializer boot = new Application();
//		子 protected 本类和同包类
//		
//		父 public    所有地方
//		boot.run()
//		子类
//		b
//		boot = new Application1();
//		
//		c
//		boot = new Application2();
//		
//		boot.onStartup(servletContext);
//		boot.onStartup(servletContext);
//		boot.onStartup(servletContext);
//		instanceof 
//		(Application1)b.
//		
//	}
	public static void main(String[] args) throws IOException {
		File file = new File("..");
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getCanonicalPath());
	}
}
