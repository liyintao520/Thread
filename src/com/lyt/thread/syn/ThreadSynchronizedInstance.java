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
public class ThreadSynchronizedInstance {
	public static void main(String[] args) {
		Jvm jvm1 = Jvm.getInstance();
		Jvm jvm2 = Jvm.getInstance();
		//�ڵ��߳����������õĵ�ַ��һ����
		System.out.println("�ڵ��߳����������õĵ�ַ��һ����:");
		System.out.println(jvm1);
		System.out.println(jvm2);
		System.out.println("--------���߳����õ�ַ---------------");
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
		System.out.println(Thread.currentThread().getName() + "-->������" + Jvm.getInstance(time));
	}
}
/**
 * �������ģʽ
 * ȷ��һ����ֻ��һ������
 * ����ʽ
 * 1��������˽�л��������ⲿֱ�Ӵ�������
 * 2������һ��˽�еľ�̬����
 * 3������һ������Ĺ����ľ�̬���� ���ʸñ������������û�ж��󣬴����ö���
 * @author Administrator
 */
class Jvm{
	//����һ��˽�еľ�̬����
	private static Jvm instance = null;//��������ô�����������ʹ�õ�ʱ�򴴽�����
	//������˽�л��������ⲿֱ�Ӵ�������
	private Jvm(){
		
	}
	//����һ������Ĺ����ľ�̬���� ���ʸñ������������û�ж��󣬴����ö���
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
	//����ʽ������� double checking
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