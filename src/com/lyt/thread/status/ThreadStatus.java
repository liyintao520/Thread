package com.lyt.thread.status;
/**
 * �߳�״̬
 * @author Administrator
 *
 */
public class ThreadStatus {
	public static void main(String[] args) {
		Study s = new Study();
		new Thread(s).start();
		
		//�ⲿ����
		for (int i = 0; i < 5; i++) {
			if(3 == i){
				s.stop();//�޸�flagֵ
			}
			System.out.println("main������������--��" + i);
		}
	}
}
class Study implements Runnable{
	//1���߳����ж����߳���ʹ�õı�ʶ
	private boolean flag = true;
	@Override
	public void run() {
		//2���߳���ʹ�øñ�ʶ
		while(flag){
			System.out.println("study thread...");
		}
	}
	//3�������ṩ�����ı�ı�ʶ
	public void stop(){
		this.flag = false;
	}
}