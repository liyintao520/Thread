package com.lyt.thread.schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer类
 * schedule(TimerTask task, Date time) 
 * 		安排在指定的时间执行指定的任务。
 * schedule(TimerTask task, Date firstTime, long period)  
 * 		安排指定的任务在指定的时间开始进行重复的固定延迟执行。
 * schedule(TimerTask task, long delay)  
 * 		安排在指定延迟后执行指定的任务。
 * schedule(TimerTask task, long delay, long period) 
 * 		安排指定的任务从指定的延迟后开始进行重复的固定延迟执行。
 * scheduleAtFixedRate(TimerTask task, Date firstTime, long period) 
 * 		安排指定的任务在指定的时间开始进行重复的固定速率执行。
 * scheduleAtFixedRate(TimerTask task, long delay, long period)
 *		安排指定的任务在指定的延迟后开始进行重复的固定速率执行。
 * @author Administrator
 * 
 * 
 * 也可以自学一下Quartz框架：www.quartz-scheduler.org/
 * juc
 */
public class TimerDemo01 {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		Timer timer = new Timer();
		SimpleDateFormat newSF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat oldSF = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.ENGLISH);
		String dateResult = "";
		String defDate = new Date().toString();//默认的时间格式
		try {
			dateResult = newSF.format(oldSF.parse(defDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//Wed Jun 29 17:26:16 CST 2016======2016-06-29 17:26:16
		System.out.println(defDate + "======" + dateResult);
		System.out.println("当前时间：" + dateResult);
		timer.schedule(new TimerTask(){
				@Override
				public void run() {
					System.out.println("时间间隔：" + new Date());
					System.out.println("一秒后运行，每隔1秒运行一次");
				}
			}, new Date(System.currentTimeMillis()+1000), 1000);//一秒后运行，每隔1秒运行一次
	}

}
