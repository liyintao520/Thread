package com.lyt.thread.syn;
/**
 * ����������ʽ--�������Ҫ����������⣬����Ҫ�õ�������������ģʽ��
 * �����ͬ�����������������
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
	//�����ȸ��һ������ڸ���Ǯ
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
		System.out.println("һ�ָ�Ǯ��");
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
	//�����ȸ���Ǯ�����ڸ������
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
		System.out.println("һ�ָ�����");
	}
}