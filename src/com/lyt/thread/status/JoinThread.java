package com.lyt.thread.status;
/**
 * Join : �ϲ��߳�
 * t.join()--���������ȴ����߳���ֹ
 * @author Administrator
 *
 */
public class JoinThread extends Thread {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		JoinThread jt = new JoinThread();
		Thread t = new Thread(jt);//�����߳�
		t.start();//����״̬
		//cpu ��������
		for (int i = 0; i < 10; i++) {
			if(5 == i){
				System.out.println("��i=5��ʱ��main�������״̬��JoinThread�߳�������֮���ڿ�ʼ����main������");
				t.join();//��i=5��ʱ��main�������״̬������һ���س�������֮���ڿ�ʼ����main������
				System.out.println("JoinThread�߳�������֮���ڿ�ʼ����main������");
			}
			System.out.println("main......" + i);
		}
	}
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Join......" + i);
		}
	}
}
