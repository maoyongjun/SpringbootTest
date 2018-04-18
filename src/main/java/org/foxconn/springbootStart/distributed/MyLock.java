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
	ThreadLocal<String> local = new ThreadLocal<String>();
	
	private String requestId = UUID.randomUUID().toString();
	private String lock;
	public MyLock(){
		try {
			
			jedis = new Jedis("127.0.0.1",6379);
			
		} catch (Exception e) {
			logger.info("error:"+e.getMessage());
		}
	}
	@Override
	public void lock() {
		if(!("lock").equals(lock)){
			local.set(requestId);
			lock ="lock";
			logger.info("获取锁成功");
		}else{
			try {
				logger.info("获取锁失败");
				wait();
			} catch (Exception e) {
				logger.error(e.getCause());
			}
			
		}
//		logger.info("lockkey--->"+jedis.get("lockkey"));
//		if(null!=jedis.get("lockkey")){
//			logger.info("获取锁失败");
//			try {
//				wait();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			return;
//		}
//		jedis.set("lockkey",requestId);
//		logger.info("获取锁成功");
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		
		
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
		
//		if(LockUtil.releaseDistributedLock(jedis,  "lockkey", requestId)){
//			logger.info("解锁失败");
//			return;
//		}
//		logger.info("解锁成功");
//		requestId = UUID.randomUUID().toString();
		
		if(("lock").equals(lock)){
			String id = local.get();
			if(requestId.equals(id)){
				lock ="lock";
				logger.info("解锁成功");
				lock="";
				requestId = UUID.randomUUID().toString();
			}else{
				logger.info("解锁失败");
				try {
					wait();
				} catch (Exception e) {
					logger.info(e.getCause());
				}
				unlock();
			}
		}
	}

	@Override
	public Condition newCondition() {
		
		return null;
	}
	
}
