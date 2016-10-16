package com.lyt.thread.pro;
/**
 * һ������������ͬ����Դ���������̵߳���
 * ������������ģʽ--���źŵƷ���
 * wait():�ȴ����ͷ��� sleep���ͷ���
 * notify()/notifyAll()����
 * ע�⣺wait()��notify()���� ��synchronizedһ��ʹ�õ�   û��ͬ���͵ȴ�����
 * @author liyintao
 * �ȴ�����ѭԭ��
 * 1����ȡ���������2��������������㣬��ô���ö����wait()��������֪ͨ����Ҫ���������3���콣������ִ�ж�Ӧ���߼�
 * synchronized(����){
 * 	while(����������){
 *  	����.wait();
 *  }
 *  ��Ӧ�Ĵ����߼�...
 * }
 * ֪ͨ����ѭԭ��
 * 1����ö��������2���ı�������3��֪ͨ���еȴ��ڶ�����̡߳�
 * synchronized(����){
 * 	�ı�����
 * 	����.notify()/notifyAll();
 * }
 */
public class Movie {
	private String pic;
	//�źŵƷ�
	//flag -->T	�����������������ߵȴ���������ɺ�֪ͨ����
	//flag -->F	���������ѣ������ߵȴ���������ɺ�֪ͨ����
	private boolean flag = true;
	/**
	 * ����--������
	 * @param pic
	 */
	public synchronized void play(String pic){
		if(!flag){//�����ߵȴ�
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//��ʼ����
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("�����ˣ�" + pic);
		//�������
		this.pic = pic;
		//֪ͨ������
		this.notify();
		//������ͣ��
		this.flag = false;
	}
	/**
	 * �ۿ�--������
	 */
	public synchronized void watch(){
		if(flag){//�����ߵȴ�
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//��ʼ����
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//�������
		System.out.println("�����ˣ�" + pic);
		//֪ͨ����
		this.notifyAll();
		//����ֹͣ
		this.flag = true;
	}
}
