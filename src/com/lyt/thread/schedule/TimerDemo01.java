package com.lyt.thread.schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer��
 * schedule(TimerTask task, Date time) 
 * 		������ָ����ʱ��ִ��ָ��������
 * schedule(TimerTask task, Date firstTime, long period)  
 * 		����ָ����������ָ����ʱ�俪ʼ�����ظ��Ĺ̶��ӳ�ִ�С�
 * schedule(TimerTask task, long delay)  
 * 		������ָ���ӳٺ�ִ��ָ��������
 * schedule(TimerTask task, long delay, long period) 
 * 		����ָ���������ָ�����ӳٺ�ʼ�����ظ��Ĺ̶��ӳ�ִ�С�
 * scheduleAtFixedRate(TimerTask task, Date firstTime, long period) 
 * 		����ָ����������ָ����ʱ�俪ʼ�����ظ��Ĺ̶�����ִ�С�
 * scheduleAtFixedRate(TimerTask task, long delay, long period)
 *		����ָ����������ָ�����ӳٺ�ʼ�����ظ��Ĺ̶�����ִ�С�
 * @author Administrator
 * 
 * 
 * Ҳ������ѧһ��Quartz��ܣ�www.quartz-scheduler.org/
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
		String defDate = new Date().toString();//Ĭ�ϵ�ʱ���ʽ
		try {
			dateResult = newSF.format(oldSF.parse(defDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//Wed Jun 29 17:26:16 CST 2016======2016-06-29 17:26:16
		System.out.println(defDate + "======" + dateResult);
		System.out.println("��ǰʱ�䣺" + dateResult);
		timer.schedule(new TimerTask(){
				@Override
				public void run() {
					System.out.println("ʱ������" + new Date());
					System.out.println("һ������У�ÿ��1������һ��");
				}
			}, new Date(System.currentTimeMillis()+1000), 1000);//һ������У�ÿ��1������һ��
	}

}
