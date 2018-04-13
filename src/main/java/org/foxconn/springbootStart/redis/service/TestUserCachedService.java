package org.foxconn.springbootStart.redis.service;

import org.foxconn.springbootStart.redis.entity.User;

public interface TestUserCachedService {
	public User getuser(String name);
}
