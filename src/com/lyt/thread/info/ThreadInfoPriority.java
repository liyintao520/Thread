package com.lyt.thread.info;
/**
 * 线程优先级
 * 优先级：概率，不是绝对的优先级  只是运行的概率偏高
 * MAX_PRIORITY  10 最高
 * NORM_PRIORITY 5  默认优先级
 * MIN_PRIORITY  1  最低
 * @author liyintao
 * setPriority(Thread.MIN_PRIORITY) 设置优先级
 * setPriority()
 */
public class ThreadInfoPriority {
	public static void main(String[] args) throws InterruptedException {
		//normPriority();//默认优先级
		minPriority();
	}
	//设置优先级：执行顺序 概率大小
	public static void minPriority() throws InterruptedException{
		MyThread it1 = new MyThread();
		Thread p1 = new Thread(it1, "lyt");
		MyThread it2 = new MyThread();
		Thread p2 = new Thread(it2, "wlj");
		//设置优先级
		p1.setPriority(Thread.MIN_PRIORITY);
		p2.setPriority(Thread.MAX_PRIORITY);
		p1.start();
		p2.start();
		Thread.sleep(5);
		it1.stop();
		it2.stop();
	}
	//默认的优先级：执行顺序  轮流
	public static void normPriority() throws InterruptedException{
		MyThread it1 = new MyThread();
		Thread p1 = new Thread(it1, "lyt");
		MyThread it2 = new MyThread();
		Thread p2 = new Thread(it2, "wlj");
		p1.start();
		p2.start();

		Thread.sleep(2);
		it1.stop();
		it2.stop();
	}
}
