package org.foxconn.springbootStart.redis.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.foxconn.springbootStart.redis.entity.City;
import org.foxconn.springbootStart.redis.entity.User;
import org.foxconn.springbootStart.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService{

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private Set<User> users = new HashSet<User>();

    private Set<City> cities = new HashSet<City>();

    @CachePut(value = "user", key = "'User:'+#user.name")
    public User addUser(User user) {
        users.add(user);
        return user;
    }

    @Cacheable(value = "user", key = "'User:'+#name")
    public User addUser(String name ,String password) {
        User user = new User(name, password);
        users.add(user);
        return user;
    }

    @Cacheable(value = "user", key = "'User:'+#name")
    public User getStudent(String name) {
        System.out.println("not in redis cache");
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    @CachePut(value = "city", key = "'City:'+#city.cityname")
    public City addCity(City city) {
        cities.add(city);
        return city;
    }
}
