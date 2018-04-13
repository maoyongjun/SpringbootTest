package org.foxconn.springbootStart.service;

import org.apache.log4j.Logger;
import org.foxconn.springbootStart.DataSourceAspect;
import org.junit.Test;
import org.springframework.stereotype.Service;
@Service
public class TestService {
	private Logger logger = Logger.getLogger(TestService.class);
	@DataSourceAspect("db1")
	@Test
	public void test(){
		logger.info("test service");
	}
}
