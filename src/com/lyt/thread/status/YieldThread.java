package com.lyt.thread.status;
/**
 * Join : �ϲ��߳�
 * t.yield()--����������ͣ��ǰ����ִ�е��̶߳��󣬲�ִ�������߳�
 * @author Administrator
 *
 */
public class YieldThread extends Thread {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		YieldThread jt = new YieldThread();
		Thread t = new Thread(jt);//�����߳�
		t.start();//����״̬
		//cpu ��������
		for (int i = 0; i < 10; i++) {
			if(i%2==0){
				Thread.yield();//д���Ǹ��߳��У�����ͣ�Ǹ��̣߳���ʱ��ͣmain�߳�
				System.out.println("д���Ǹ��߳��У�����ͣ�Ǹ��̣߳���ʱ��ͣmain�̡߳�����");
			}
			System.out.println("main......" + i);
		}
	}
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("yield......" + i);
		}
	}
}
