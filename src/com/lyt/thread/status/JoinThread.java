package com.lyt.thread.status;
/**
 * Join : 合并线程
 * t.join()--方法代表：等待该线程终止
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
		Thread t = new Thread(jt);//新生线程
		t.start();//就绪状态
		//cpu 调度运行
		for (int i = 0; i < 10; i++) {
			if(5 == i){
				System.out.println("当i=5的时候，main变成阻塞状态，JoinThread线程运行完之后，在开始运行main。。。");
				t.join();//当i=5的时候，main变成阻塞状态，另外一个县城运行完之后，在开始运行main。。。
				System.out.println("JoinThread线程运行完之后，在开始运行main。。。");
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
