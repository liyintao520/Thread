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
public class ThreadSynchronizedInstance {
	public static void main(String[] args) {
		Jvm jvm1 = Jvm.getInstance();
		Jvm jvm2 = Jvm.getInstance();
		//在单线程中他们引用的地址是一样的
		System.out.println("在单线程中他们引用的地址是一样的:");
		System.out.println(jvm1);
		System.out.println(jvm2);
		System.out.println("--------多线程引用地址---------------");
		JvmThread thread1 = new JvmThread(100);
		JvmThread thread2 = new JvmThread(100);
		thread1.start();
		thread2.start();
		System.out.println(thread1);
		System.out.println(thread2);
	}
}
class JvmThread extends Thread{
	private long time;
	public JvmThread(){}
	public JvmThread(long time){
		this.time = time;
	}
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "-->创建：" + Jvm.getInstance(time));
	}
}
/**
 * 单例设计模式
 * 确保一个雷只有一个对象
 * 懒汉式
 * 1、构造器私有化，避免外部直接创建对象
 * 2、声明一个私有的静态变量
 * 3、创建一个对外的公共的静态方法 访问该变量，如果变量没有对象，创建该对象
 * @author Administrator
 */
class Jvm{
	//声明一个私有的静态变量
	private static Jvm instance = null;//这块我懒得创建对象，在你使用的时候创建对象
	//构造器私有化，避免外部直接创建对象
	private Jvm(){
		
	}
	//创建一个对外的公共的静态方法 访问该变量，如果变量没有对象，创建该对象
	public static Jvm getInstance() {
		if(null == instance){
			instance = new Jvm();
		}
		return instance;
	}
	
	public static Jvm getInstance(long time) {
		if(null == instance){
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			instance = new Jvm();
		}
		return instance;
	}
//	public static synchronized Jvm getInstanceSynchronized(long time) {
//		if(null == instance){
//			try {
//				Thread.sleep(time);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			instance = new Jvm();
//		}
//		return instance;
//	}
	public static Jvm getInstanceSynchronized(long time) {
		synchronized (Jvm.class) {
			if(null == instance){
				try {
					Thread.sleep(time);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				instance = new Jvm();
			}
			return instance;
		}
	}
	//懒汉式：经典的 double checking
	public static Jvm getInstance1(long time) {
		if(null == instance){
			synchronized (Jvm.class) {
				try {
					Thread.sleep(time);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				instance = new Jvm();
			}
		}
			return instance;
	}
}