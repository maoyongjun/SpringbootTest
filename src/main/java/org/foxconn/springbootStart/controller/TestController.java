package org.foxconn.springbootStart.controller;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.foxconn.springbootStart.service.TestService;
import org.foxconn.springbootStart.util.ReflectUtil;
import org.junit.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	private Logger logger = Logger.getLogger(TestController.class);
	@Resource
	private DataSource db1;
	@Resource
	private TestService testService;
    @GetMapping("/helloworld")
    @Test
    public void helloworld() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
//    	testService.test();
    	ReflectUtil.invote("org.foxconn.springbootStart.service.TestService","test");
//        return "helloworld";
    }
    @GetMapping("/helloworld1")
    public String helloworld1() {
    	logger.info(db1);
        return "helloworld3";
    }
    
    @GetMapping("/helloworld3")
    public String helloworld3() {
    	testService.test();
        return "helloworld3";
    }
}
