package com.lyt.thread.pool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * �̳߳ؽӿڵ�Ĭ��ʵ��
 * public static <T> List<T> synchronizedList(List<T> list)
 * 	����ָ���б�֧�ֵ�ͬ�����̰߳�ȫ�ģ��б�Ϊ�˱�֤��˳����ʣ�����ͨ�����ص��б�������жԵײ�ʵ���б�ķ��ʡ�
 * @author liyintao
 * @param <Job>
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job>{
	//�̳߳����������
	private static final int MAX_WORKER_NUMBERS = 10;
	//Ĭ���̳߳ص�����
	private static final int DEFAULT_WORKER_NUMBERS = 5;
	//�̳߳���С����
	private static final int MIN_WORKER_NUMBERS = 1;
	//����һ�������б�������������빤��
	private final LinkedList<Job> jobs = new LinkedList<Job>();
	//�������б�
	private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());//Collections.synchronizedListת�����̰߳�ȫ����
	//�������߳�����
	private int workerNum = DEFAULT_WORKER_NUMBERS ;
	//�̱߳������
	private AtomicLong threadNum = new AtomicLong();
	
	public DefaultThreadPool(){
		initializeWokers(DEFAULT_WORKER_NUMBERS);
	}
	/**
	 * �����������߳��� �������ģ�С����С�ľ�����С��
	 * @param num
	 */
	public DefaultThreadPool(int num){
		workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : num < MIN_WORKER_NUMBERS ? MIN_WORKER_NUMBERS : num;
		initializeWokers(workerNum);
	}
	@Override
	public void execute(Job job) {
		if(job != null){
			//���һ��������Ȼ�����֪ͨ
			synchronized(jobs){
				jobs.add(job);
				jobs.notify();//�����ڴ˶���������ϵȴ��ĵ����߳�
			}
		}
	}

	@Override
	public void shutdown() {
		for(Worker worker : workers){
			worker.shutdown();//�ı� ��ʶ
		}
	}

	@Override
	public void addWorkers(int num) {
		synchronized(jobs){
			//����������Worker�������ܳ������ֵ
			if(num + this.workerNum > MAX_WORKER_NUMBERS){
				num = MAX_WORKER_NUMBERS - this.workerNum;
			}
			initializeWokers(num);
			this.workerNum += num;
		}
	}

	@Override
	public void removeWorker(int num) {
		synchronized(jobs){
			if(num >= this.workerNum){
				throw new IllegalArgumentException("beyond workNum");
			}
			//���ո���������ֹͣWorker
			int count = 0;
			while(count <num){
				Worker worker = workers.get(count);
				if(workers.remove(worker)){
					count++;
				}
			}
			this.workerNum -= count;
		}
	}

	@Override
	public int getJobSize() {
		return jobs.size();
	}
	/**
	 * ��ʼ���̹߳�����
	 * @param num
	 */
	private void initializeWokers(int num) {
		for(int i =0; i <= num; i++){
			Worker worker = new Worker();
			workers.add(worker);
			// incrementAndGet() ��ԭ�ӷ�ʽ����ǰֵ�� 1��
			Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet());
			thread.start();
		}
	}
	/**
	 * �����ߣ�������������
	 */
	class Worker implements Runnable{
		//�Ƿ���----�� volatile �����ֶ� ���Ǹ�֪�����˺ͶӸñ����ķ��ʾ���Ҫ�ӹ����ڴ��л�ȡ���������ĸı����ͬ��ˢ�»ع����ڴ棬���ܹ���֤�����̶߳ӱ������ʵĿɼ���
		private volatile boolean running = true;
		
		@Override
		public void run() {
			while(running){
				Job job = null;
				synchronized(jobs){
					//����������б�Ϊ�գ���ô�õȴ�wait
					while(jobs.isEmpty()){
						try {
							jobs.wait();
						} catch (InterruptedException e) {
							//��֪���ⲿ��WorkerThread���ն˲������ͷ���
							Thread.currentThread().interrupt();//�ж��̡߳�
							return ;
						}
					}
					//ȡ��һ��Job
					job = jobs.removeFirst();//LinkedList  removeFirst()�Ƴ������ش��б�ĵ�һ��Ԫ�ء�
				}
				if(job != null){
					try {
						job.run();
					} catch (Exception ex) {
						//����Jobִ���е�Exception
					}
				}
			}
		}
		
		public void shutdown() {
			running = false;
		}

	}
}
