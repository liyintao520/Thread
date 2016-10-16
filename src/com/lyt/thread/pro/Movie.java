package com.lyt.thread.pro;
/**
 * 一个场景，即共同的资源。被两个线程调用
 * 生产者消费者模式--【信号灯法】
 * wait():等待，释放锁 sleep不释放锁
 * notify()/notifyAll()唤醒
 * 注意：wait()、notify()方法 与synchronized一起使用的   没有同步就等待不了
 * @author liyintao
 * 等待方遵循原则：
 * 1、获取对象的锁；2、如果条件不满足，那么调用对象的wait()方法，被通知后仍要检查条件；3、天剑满足则执行对应的逻辑
 * synchronized(对象){
 * 	while(条件不满足){
 *  	对象.wait();
 *  }
 *  对应的处理逻辑...
 * }
 * 通知方遵循原则：
 * 1、获得对象的锁；2、改变条件；3、通知所有等待在对象的线程。
 * synchronized(对象){
 * 	改变条件
 * 	对象.notify()/notifyAll();
 * }
 */
public class Movie {
	private String pic;
	//信号灯法
	//flag -->T	生产者生产，消费者等待，生产完成后通知消费
	//flag -->F	消费者消费，生产者等待，消费完成后通知生产
	private boolean flag = true;
	/**
	 * 播放--生产者
	 * @param pic
	 */
	public synchronized void play(String pic){
		if(!flag){//生产者等待
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//开始生产
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("生产了：" + pic);
		//生产完毕
		this.pic = pic;
		//通知消费者
		this.notify();
		//生产者停下
		this.flag = false;
	}
	/**
	 * 观看--消费者
	 */
	public synchronized void watch(){
		if(flag){//消费者等待
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//开始消费
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//消费完毕
		System.out.println("消费了：" + pic);
		//通知生产
		this.notifyAll();
		//消费停止
		this.flag = true;
	}
}
