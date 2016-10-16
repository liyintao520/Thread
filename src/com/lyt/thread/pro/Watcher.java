package com.lyt.thread.pro;
/**
 * 消费者
 * @author Administrator
 *
 */
public class Watcher implements Runnable{
	private Movie m;
	
	public Watcher(Movie m) {
		super();
		this.m = m;
	}
	//只管看
	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			m.watch();
		}
	}

}
