package org.foxconn.springbootStart.distributed;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;

import org.apache.log4j.Logger;
import org.junit.Test;

public class DistributedDemo {
	private Logger logger = Logger.getLogger(DistributedDemo.class);
	private int poolSize = 10;
	ExecutorService service = Executors.newFixedThreadPool(poolSize);
	Lock lock = new MyLock();

	public static void main(String[] args) {
		DistributedDemo demo = new DistributedDemo();
		demo.run();
	}

	 @Test
	public void run() {
		logger.info("-----begin------");
		final CountDownLatch cd = new CountDownLatch(poolSize);
		final CyclicBarrier cb = new CyclicBarrier(10);
		for (int i = 0; i < 10; i++) {
			final int temp = i;
			logger.info("----index-->" + temp + "-----");
			service.execute(new Runnable() {
				public void run() {
					// try {
					// cb.await();
					// } catch (InterruptedException | BrokenBarrierException
					// e1) {
					// e1.printStackTrace();
					// }
					// logger.info("----id:"+Ids.getId()+"-----");
					// cd.countDown();
					// cd.await();
					lock.lock();
					// synchronized(DistributedDemo.class){
					logger.info("----id:" + Ids.getId() + "-----");
					// }
					lock.unlock();
				}
			});
		}
		service.shutdown();
	}

}
