package org.foxconn.springbootStart.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.foxconn.springbootStart.aop.DataSourceAspect;
import org.foxconn.springbootStart.dao.UserDao;
import org.junit.Test;
import org.springframework.stereotype.Service;
@Service
public class TestService {
	private Logger logger = Logger.getLogger(TestService.class);
//	@Resource
//	SqlSessionFactory sqlSessionFactory;
	@Resource
	UserDao useDao;
	
	@DataSourceAspect("db1")
	@Test
	public void test(){
		logger.info("test service");
		logger.info(useDao.getUserByName("sfcadmin"));
	}
}
