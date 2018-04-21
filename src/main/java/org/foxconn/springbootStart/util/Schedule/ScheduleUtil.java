package org.foxconn.springbootStart.util.Schedule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class ScheduleUtil {
	Logger logger = Logger.getLogger(ScheduleUtil.class);
	public static void main(String[] args) {
		ScheduleUtil test = new ScheduleUtil();
		test.test();
	}

	public void test() {
		ScheduledExecutorService taskService = Executors.newScheduledThreadPool(10);
		
		final List<Process> process = new ArrayList<Process>();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				Calendar cal =  Calendar.getInstance();
				cal.setTime(new Date());
				logger.info(cal.getFirstDayOfWeek());
				logger.info(cal.getWeekYear());
				SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
				logger.info(sdf.format(cal.getTime()));
				logger.info(cal.get(cal.DAY_OF_YEAR));
				logger.info(cal.get(cal.DAY_OF_WEEK));
				logger.info(cal.get(cal.DAY_OF_WEEK_IN_MONTH));
				logger.info(cal.get(cal.DAY_OF_MONTH));
				if(1==cal.get(cal.DAY_OF_MONTH)){//每月1号执行
					callJarCmd("D:\\cloudMesDBScript\\target\\cloudMesDBScript-0.0.1-SNAPSHOT.jar");
				}
			}
		};
		taskService.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.DAYS);
	}
	private void callJarCmd(String jar){
		try {
			Runtime r = Runtime.getRuntime();
			Process p = r.exec(new String[]{"JAVA","-Dfile.encoding=utf-8","-jar",jar});
			getMsg(p.getInputStream(),"info");
			getMsg(p.getErrorStream(),"error");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private void getMsg(InputStream in,String msgType) throws IOException{
		InputStreamReader inputreader = new InputStreamReader(in,"utf-8");
		BufferedReader bufferReader = new BufferedReader(inputreader);
		String msg;
		while(null!=(msg = bufferReader.readLine())){
			if("error".equalsIgnoreCase(msgType)){
				logger.error(msg);
			}else{
				logger.info(msg);
			}
		}
	}
}
