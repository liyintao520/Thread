package com.lyt.thread.status;
/**
 * Join : 合并线程
 * t.yield()--方法代表：暂停当前正在执行的线程对象，并执行其他线程
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
		Thread t = new Thread(jt);//新生线程
		t.start();//就绪状态
		//cpu 调度运行
		for (int i = 0; i < 10; i++) {
			if(i%2==0){
				Thread.yield();//写在那个线程中，就暂停那个线程，此时暂停main线程
				System.out.println("写在那个线程中，就暂停那个线程，此时暂停main线程。。。");
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
