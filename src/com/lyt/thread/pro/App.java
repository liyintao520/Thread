package com.lyt.thread.pro;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//��ͬ����Դ
		Movie m = new Movie();
		//���߳�
		Player p = new Player(m);
		Watcher w = new Watcher(m);
		new Thread(p).start();
		new Thread(w).start();
	}

}
