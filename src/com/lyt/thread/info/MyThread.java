package com.lyt.thread.info;
/**
 * Thread.currentThread() ��ȡ��ǰ�̶߳���
 * @author Administrator
 *
 */
public class MyThread implements Runnable{
	
	private boolean flag = true;
	private int num = 0;
	@Override
	public void run() {
		while(flag){
			System.out.println(Thread.currentThread().getName() + "-->" + num++);
		}
	}
	//�޸��̱߳�ʶ����
	public void stop(){
		this.flag = !this.flag;
	}
}
