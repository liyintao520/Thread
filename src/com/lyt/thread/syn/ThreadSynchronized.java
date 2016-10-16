package com.lyt.thread.syn;

import java.util.Hashtable;

/**
 * 线程同步，又称为并发
 * Hashtable put()方法就是同步的 故是线程安全的
 * StringBuffer append()方法也是synchronized修饰的
 * @author Administrator
 *
 * 锁定范围不正确，锁定类型不正确
 */
public class ThreadSynchronized {
	public static void main(String[] args) {
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
	private boolean flag = true;
	@Override
	public void run() {
		while(flag){
			test3();
		}
	}
	//线程安全的  并且锁定范围是正确的   --》同步块
	public void test3(){
		synchronized(this){//synchronized(引用类型|this|类.class){ }
			if(num <= 0){
				flag = false;
				return ;
			}
			//t1、t2、t3  一起进来了假如 num = 1只有一张票的时候，
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " 抢到了 " + num--);
		}
		
	}
	//线程安全的  同步方法
	public synchronized void test2(){
		if(num <= 0){
			flag = false;
			return ;
		}
		//t1、t2、t3  一起进来了假如 num = 1只有一张票的时候，
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " 抢到了 " + num--);
	}
	//线程不安全
	public void test1(){
		if(num <= 0){
			flag = false;
			return ;
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