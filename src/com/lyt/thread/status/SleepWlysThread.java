package com.lyt.thread.status;

/**
 * sleepģ��������ʱ--�������̲߳���ȫ����
 * @author Administrator
 * ���߳��漰����Դ����������Ҫ��������
 */
public class SleepWlysThread {
	public static void main(String[] args) throws InterruptedException {
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
	@Override
	public void run() {
		while(true){
			if(num <= 0){
				break;//����ѭ��--Ʊ�Ѿ�������
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
}
