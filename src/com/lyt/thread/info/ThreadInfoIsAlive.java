package com.lyt.thread.info;
/**
 * 线程状态：isAlive()
 * @author liyintao
 *
 */
public class ThreadInfoIsAlive {
	public static void main(String[] args) throws InterruptedException {
		MyThread it = new MyThread();
		Thread proxy = new Thread(it, "挨踢");
//		proxy.setName("挨踢");//如果不取名字,那么就是标号的名字:Thread-0
		System.out.println(proxy.getName());
		System.out.println("获取当前线程对象名字" + Thread.currentThread().getName());
		
		proxy.start();
		System.out.println("启动后的状态：" + proxy.isAlive());
		Thread.sleep(1);//200毫秒之后停下来
		it.stop();//修改线程标识
		Thread.sleep(1000);//主要是停止后cpu不一定立马让他停下来
		System.out.println("停止后的状态：" + proxy.isAlive());
	}
}
