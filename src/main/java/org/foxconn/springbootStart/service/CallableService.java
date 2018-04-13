package org.foxconn.springbootStart.service;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CallableService {
	Logger logger = LoggerFactory.getLogger(CallableService.class);
	
	@Test
	public void test() throws Exception{
		
		Callable<Double> callable = new Callable<Double>() {
			@Override
			public Double call() throws Exception {
				long currentTime = new Date().getTime();
				for(long i=0;i<200000000l;i++){
					i++;
//					logger.info("count1-- "+i);
				}
				currentTime = new Date().getTime()-currentTime;
				return new Double(currentTime);
			}
		};

		Callable<Double> callable1 = new Callable<Double>() {
			@Override
			public Double call() throws Exception {
				long currentTime = new Date().getTime();
				for(long i=0;i<200000000l;i++){
					i++;
//					logger.info("count2---- "+i);
				}
				currentTime = new Date().getTime()-currentTime;
				return new Double(currentTime);
			}
		};
		Runnable futureTask1 = new FutureTask<>(callable1);
		Runnable futureTask = new FutureTask<>(callable);
		long allTime =new Date().getTime();
		Thread thread = new Thread(futureTask1);
		Thread thread1 = new Thread(futureTask);
		thread.start();
		thread1.start();
		Double result = (Double) ((FutureTask)futureTask).get();
		Double result1 = (Double) ((FutureTask)futureTask).get();
		logger.info("first callable take time: "+result);
		logger.info("second callable take time:"+result1);
		logger.info("total take time:"+(new Date().getTime() - allTime));
		
	}
	
}
