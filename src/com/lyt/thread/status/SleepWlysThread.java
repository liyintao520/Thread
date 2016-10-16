package com.lyt.thread.status;

/**
 * sleep模拟网络延时--该类是线程不安全的类
 * @author Administrator
 * 多线程涉及到资源共享所以需要并发处理
 */
public class SleepWlysThread {
	public static void main(String[] args) throws InterruptedException {
		//真是角色
		Web12306 web = new Web12306();
		//代理
		Thread t1 = new Thread(web,"路人甲");
		Thread t2 = new Thread(web,"黄牛已");
		Thread t3 = new Thread(web,"工程师");
		//启动线程
		t1.start();
		t2.start();
		t3.start();
	}
}
class Web12306 implements Runnable{
	private int num = 10;
	@Override
	public void run() {
		while(true){
			if(num <= 0){
				break;//跳出循环--票已经卖完了
			}
			//t1、t2、t3  一起进来了假如 num = 1只有一张票的时候，
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " 抢到了 " + num--);
		//t1拿到1 之后睡觉了，b醒来之后 只能拿 0 （num--）了 c 只能拿-1
		}
	}
}
