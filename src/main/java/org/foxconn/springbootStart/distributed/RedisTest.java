package org.foxconn.springbootStart.distributed;

import org.slf4j.Logger;

import redis.clients.jedis.Jedis;

public class RedisTest {
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(RedisTest.class);
	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1",6379);
		jedis.set("lock","lock1");
		logger.info(jedis.get("lock"));
	}
}
