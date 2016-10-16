package com.lyt.thread.info;
/**
 * �߳����ȼ�
 * ���ȼ������ʣ����Ǿ��Ե����ȼ�  ֻ�����еĸ���ƫ��
 * MAX_PRIORITY  10 ���
 * NORM_PRIORITY 5  Ĭ�����ȼ�
 * MIN_PRIORITY  1  ���
 * @author liyintao
 * setPriority(Thread.MIN_PRIORITY) �������ȼ�
 * setPriority()
 */
public class ThreadInfoPriority {
	public static void main(String[] args) throws InterruptedException {
		//normPriority();//Ĭ�����ȼ�
		minPriority();
	}
	//�������ȼ���ִ��˳�� ���ʴ�С
	public static void minPriority() throws InterruptedException{
		MyThread it1 = new MyThread();
		Thread p1 = new Thread(it1, "lyt");
		MyThread it2 = new MyThread();
		Thread p2 = new Thread(it2, "wlj");
		//�������ȼ�
		p1.setPriority(Thread.MIN_PRIORITY);
		p2.setPriority(Thread.MAX_PRIORITY);
		p1.start();
		p2.start();
		Thread.sleep(5);
		it1.stop();
		it2.stop();
	}
	//Ĭ�ϵ����ȼ���ִ��˳��  ����
	public static void normPriority() throws InterruptedException{
		MyThread it1 = new MyThread();
		Thread p1 = new Thread(it1, "lyt");
		MyThread it2 = new MyThread();
		Thread p2 = new Thread(it2, "wlj");
		p1.start();
		p2.start();

		Thread.sleep(2);
		it1.stop();
		it2.stop();
	}
}
