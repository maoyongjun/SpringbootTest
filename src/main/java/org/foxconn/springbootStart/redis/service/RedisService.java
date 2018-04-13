package org.foxconn.springbootStart.redis.service;

import org.foxconn.springbootStart.redis.entity.City;
import org.foxconn.springbootStart.redis.entity.User;

public interface RedisService {

	public User addUser(User user);

	public User addUser(String name, String password);

	public User getStudent(String name);

	public City addCity(City city);
}
