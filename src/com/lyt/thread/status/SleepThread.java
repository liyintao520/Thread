package com.lyt.thread.status;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 倒计时
 * 
 * @author Administrator
 *
 */
public class SleepThread {
	public static void main(String[] args) throws InterruptedException {
		Date endTime = new Date(System.currentTimeMillis() + 10*1000);//当前时间后十秒
		long end = endTime.getTime();
		while(true){
			//parse(source)是转换为时间日期
			//format(endTime)是转换为字符串
			//先输出
			System.out.println(new SimpleDateFormat("mm:ss").format(endTime));//打印分钟毫秒 MM 是月份
			//构建下一秒的时间
			Thread.sleep(1000);
			endTime = new Date(endTime.getTime() - 1000);
			//10秒以内 继续  否则 退出
			if(end - 10000 > endTime.getTime()){
				break;
			}
			
		}
	}
}
