package com.lyt.thread.pool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程池接口的默认实现
 * public static <T> List<T> synchronizedList(List<T> list)
 * 	返回指定列表支持的同步（线程安全的）列表。为了保证按顺序访问，必须通过返回的列表完成所有对底层实现列表的访问。
 * @author liyintao
 * @param <Job>
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job>{
	//线程池最大限制数
	private static final int MAX_WORKER_NUMBERS = 10;
	//默认线程池的数量
	private static final int DEFAULT_WORKER_NUMBERS = 5;
	//线程池最小数量
	private static final int MIN_WORKER_NUMBERS = 1;
	//这是一个工作列表，将会向里面插入工作
	private final LinkedList<Job> jobs = new LinkedList<Job>();
	//工作者列表
	private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());//Collections.synchronizedList转换成线程安全的类
	//工作者线程数量
	private int workerNum = DEFAULT_WORKER_NUMBERS ;
	//线程编号生成
	private AtomicLong threadNum = new AtomicLong();
	
	public DefaultThreadPool(){
		initializeWokers(DEFAULT_WORKER_NUMBERS);
	}
	/**
	 * 如果大于最大线程数 就用最大的，小于最小的就用最小的
	 * @param num
	 */
	public DefaultThreadPool(int num){
		workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : num < MIN_WORKER_NUMBERS ? MIN_WORKER_NUMBERS : num;
		initializeWokers(workerNum);
	}
	@Override
	public void execute(Job job) {
		if(job != null){
			//添加一个工作，然后进行通知
			synchronized(jobs){
				jobs.add(job);
				jobs.notify();//唤醒在此对象监视器上等待的单个线程
			}
		}
	}

	@Override
	public void shutdown() {
		for(Worker worker : workers){
			worker.shutdown();//改变 标识
		}
	}

	@Override
	public void addWorkers(int num) {
		synchronized(jobs){
			//限制新增的Worker数量不能超过最大值
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
			//按照给定的数量停止Worker
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
	 * 初始化线程工作者
	 * @param num
	 */
	private void initializeWokers(int num) {
		for(int i =0; i <= num; i++){
			Worker worker = new Worker();
			workers.add(worker);
			// incrementAndGet() 以原子方式将当前值加 1。
			Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet());
			thread.start();
		}
	}
	/**
	 * 工作者，负责消费任务
	 */
	class Worker implements Runnable{
		//是否工作----用 volatile 修饰字段 就是告知程序人和队该变量的访问军需要从共享内存中获取，而对他的改变必须同步刷新回共享内存，它能够保证所有线程队变量访问的可见性
		private volatile boolean running = true;
		
		@Override
		public void run() {
			while(running){
				Job job = null;
				synchronized(jobs){
					//如果工作者列表为空，那么久等待wait
					while(jobs.isEmpty()){
						try {
							jobs.wait();
						} catch (InterruptedException e) {
							//感知到外部队WorkerThread的终端操作，就返回
							Thread.currentThread().interrupt();//中断线程。
							return ;
						}
					}
					//取出一个Job
					job = jobs.removeFirst();//LinkedList  removeFirst()移除并返回此列表的第一个元素。
				}
				if(job != null){
					try {
						job.run();
					} catch (Exception ex) {
						//忽略Job执行中的Exception
					}
				}
			}
		}
		
		public void shutdown() {
			running = false;
		}

	}
}
