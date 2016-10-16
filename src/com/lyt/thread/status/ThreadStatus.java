package com.lyt.thread.status;
/**
 * 线程状态
 * @author Administrator
 *
 */
public class ThreadStatus {
	public static void main(String[] args) {
		Study s = new Study();
		new Thread(s).start();
		
		//外部干涉
		for (int i = 0; i < 5; i++) {
			if(3 == i){
				s.stop();//修改flag值
			}
			System.out.println("main。。。。。。--》" + i);
		}
	}
}
class Study implements Runnable{
	//1、线程类中定义线程体使用的标识
	private boolean flag = true;
	@Override
	public void run() {
		//2、线程体使用该标识
		while(flag){
			System.out.println("study thread...");
		}
	}
	//3、对外提供方法改变的标识
	public void stop(){
		this.flag = false;
	}
}