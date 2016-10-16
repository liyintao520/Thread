package com.lyt.thread.syn;
/**
 * 死锁表现形式--【如果需要解决死锁问题，就需要用到生产者消费者模式】
 * 过多的同步方法可能造成死锁
 * @author liyintao
 */
public class ThreadSiSuo {
	public static void main(String[] args) {
		Object goods = new Object();
		Object money = new Object();
		Test t1= new Test(goods,money);
		Test2 t2= new Test2(goods,money);
		Thread proxy = new Thread(t1);
		Thread proxy2 = new Thread(t2);
		proxy.start();
		proxy2.start();
	}
	
}
class Test implements Runnable{
	Object goods;
	Object money;
	public Test(Object goods, Object money) {
		super();
		this.goods = goods;
		this.money = money;
	}
	@Override
	public void run() {
		while(true){
			test();
		}
	}
	//锁着先给我货物我在给你钱
	public void test(){
		synchronized(goods){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(money){
				
			}
		}
		System.out.println("一手给钱！");
	}
}

class Test2 implements Runnable{
	Object goods;
	Object money;
	
	public Test2(Object goods, Object money) {
		super();
		this.goods = goods;
		this.money = money;
	}
	@Override
	public void run() {
		while(true){
			test();
		}
	}
	//锁着先给我钱，我在给你货物
	public void test(){
		synchronized(money){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(goods){
				
			}
		}
		System.out.println("一手给货！");
	}
}