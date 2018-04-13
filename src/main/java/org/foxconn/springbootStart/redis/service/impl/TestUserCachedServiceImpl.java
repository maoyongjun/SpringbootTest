package org.foxconn.springbootStart.redis.service.impl;

import org.foxconn.springbootStart.redis.entity.User;
import org.foxconn.springbootStart.redis.service.TestUserCachedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TestUserCachedServiceImpl implements TestUserCachedService{
	private  Logger logger=LoggerFactory.getLogger(this.getClass());
	@Cacheable(key="#name",value={"user"})
//	@CachePut(key="#name",value={"user"})
	public User getuser(String name) {
		User user = new User("qiangzhu", "qiangzhu");
		logger.info(name+"-->get data from DB...");
		return user;
	}

}
