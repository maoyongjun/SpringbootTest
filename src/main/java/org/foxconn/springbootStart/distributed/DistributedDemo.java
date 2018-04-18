package org.foxconn.springbootStart.distributed;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.omg.CORBA.portable.RemarshalException;

import redis.clients.jedis.Jedis;

public class DistributedDemo {
	private Logger logger = Logger.getLogger(DistributedDemo.class);
	private int poolSize = 20;
	ExecutorService service = Executors.newFixedThreadPool(poolSize);
	private Jedis jedis = new Jedis("127.0.0.1",6379);

	public static void main(String[] args) {
		DistributedDemo demo = new DistributedDemo();
		demo.run();
	}

	@Test
	public void run() {
		final Lock lock = new MyLock();
		final Lock lock1 = new ReentrantLock();
		logger.info("-----begin------");
		final CountDownLatch cd = new CountDownLatch(poolSize);
		final CyclicBarrier cb = new CyclicBarrier(poolSize);
		for (int i = 0; i < poolSize; i++) {
			final int temp = i;
			logger.info("----index-->" + temp + "-----");
			service.execute(new Runnable() {
				public void run() {
					try {
						cb.await();
					} catch (InterruptedException | BrokenBarrierException e1) {
						e1.printStackTrace();
					}

					lock.lock();
//					String result = jedis.set("key","key1");
					logger.info("----id:" + Ids.getId() + "-----");
//					logger.info("----key:result-->" +""+":"+ jedis.get("key") + "-----");
					lock.unlock();
					cd.countDown();
				}
			});
		}
		try {
			cd.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		service.shutdown();
	}

}
