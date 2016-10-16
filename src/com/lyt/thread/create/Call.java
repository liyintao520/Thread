package com.lyt.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Call {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//�����߳�
		ExecutorService ser = Executors.newFixedThreadPool(1);
		Race tortoise = new Race();
		//��ȡֵ
		Future<Integer> result = ser.submit(tortoise);
		int num = result.get();
		System.out.println(num);
		ser.shutdownNow();//����ֹͣ
//		ser.shutdown();//ֹͣ
	}
	
}

class Race implements Callable<Integer>{
	@Override
	public Integer call() throws Exception {
		return 1000;
	}
}

