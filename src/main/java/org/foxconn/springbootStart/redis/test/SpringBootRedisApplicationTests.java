package org.foxconn.springbootStart.redis.test;

import org.foxconn.springbootStart.redis.entity.City;
import org.foxconn.springbootStart.redis.entity.User;
import org.foxconn.springbootStart.redis.service.RedisService;
import org.foxconn.springbootStart.redis.service.TestUserCachedService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRedisApplicationTests {
    private  Logger logger=LoggerFactory.getLogger(this.getClass());

//    @Autowired
//    private RedisService service;
    
    @Autowired
    private TestUserCachedService testUserCachedService;

    @Test
    public void contextLoads() {
//         User user=new User("student9","name");
//         service.addUser(user);

//         logger.info("RedisTest执行完成，return {}",service.getStudent(user.getName()).getName());
//         City city=new City("深圳1");
//         service.addCity(city);
         
         testUserCachedService.getuser("aa2");
         testUserCachedService.getuser("aa2");
         testUserCachedService.getuser("bb2");
    }
}