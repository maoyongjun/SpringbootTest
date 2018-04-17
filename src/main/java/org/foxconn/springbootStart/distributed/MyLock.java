package org.foxconn.springbootStart.distributed;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;

public class MyLock implements Lock {
	private Logger logger = Logger.getLogger(MyLock.class);
	private Jedis  jedis;
	private String requestId = UUID.randomUUID().toString();
	public MyLock(){
		super();
		jedis = new Jedis("127.0.0.1",6379);
	}
	@Override
	public void lock() {
		tryLock();
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean tryLock() {
		return LockUtil.tryGetDistributedLock(jedis, "lockkey", requestId, 20000);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unlock() {
		LockUtil.releaseDistributedLock(jedis,  "lockkey", requestId);
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
