package com.lyt.thread.syn;

import java.util.Hashtable;

/**
 * �߳�ͬ�����ֳ�Ϊ����
 * Hashtable put()��������ͬ���� �����̰߳�ȫ��
 * StringBuffer append()����Ҳ��synchronized���ε�
 * @author Administrator
 *
 * ������Χ����ȷ���������Ͳ���ȷ
 */
public class ThreadSynchronized {
	public static void main(String[] args) {
		//���ǽ�ɫ
		Web12306 web = new Web12306();
		//����
		Thread t1 = new Thread(web,"·�˼�");
		Thread t2 = new Thread(web,"��ţ��");
		Thread t3 = new Thread(web,"����ʦ");
		//�����߳�
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
	//�̰߳�ȫ��  ����������Χ����ȷ��   --��ͬ����
	public void test3(){
		synchronized(this){//synchronized(��������|this|��.class){ }
			if(num <= 0){
				flag = false;
				return ;
			}
			//t1��t2��t3  һ������˼��� num = 1ֻ��һ��Ʊ��ʱ��
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " ������ " + num--);
		}
		
	}
	//�̰߳�ȫ��  ͬ������
	public synchronized void test2(){
		if(num <= 0){
			flag = false;
			return ;
		}
		//t1��t2��t3  һ������˼��� num = 1ֻ��һ��Ʊ��ʱ��
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " ������ " + num--);
	}
	//�̲߳���ȫ
	public void test1(){
		if(num <= 0){
			flag = false;
			return ;
		}
		//t1��t2��t3  һ������˼��� num = 1ֻ��һ��Ʊ��ʱ��
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " ������ " + num--);
		//t1�õ�1 ֮��˯���ˣ�b����֮�� ֻ���� 0 ��num--���� c ֻ����-1
	}
}