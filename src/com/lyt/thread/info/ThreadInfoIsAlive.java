package com.lyt.thread.info;
/**
 * �߳�״̬��isAlive()
 * @author liyintao
 *
 */
public class ThreadInfoIsAlive {
	public static void main(String[] args) throws InterruptedException {
		MyThread it = new MyThread();
		Thread proxy = new Thread(it, "����");
//		proxy.setName("����");//�����ȡ����,��ô���Ǳ�ŵ�����:Thread-0
		System.out.println(proxy.getName());
		System.out.println("��ȡ��ǰ�̶߳�������" + Thread.currentThread().getName());
		
		proxy.start();
		System.out.println("�������״̬��" + proxy.isAlive());
		Thread.sleep(1);//200����֮��ͣ����
		it.stop();//�޸��̱߳�ʶ
		Thread.sleep(1000);//��Ҫ��ֹͣ��cpu��һ����������ͣ����
		System.out.println("ֹͣ���״̬��" + proxy.isAlive());
	}
}
