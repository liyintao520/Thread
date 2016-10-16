package com.lyt.thread.pool;
/**
 * �̳߳ؼ򵥽ӿڵĶ���
 * @author liyintao
 * @param <Job>
 */
public interface ThreadPool<Job extends Runnable> {
	/**
	 * ִ��һ��Job�����Job��Ҫʵ��Runnable
	 * @param job
	 */
	void execute(Job job);
	/**
	 * �ر��̳߳�
	 */
	void shutdown();
	/**
	 * ���ӹ������߳�
	 * @param num
	 */
	void addWorkers(int num);
	/**
	 * ���ٹ������߳�
	 * @param num
	 */
	void removeWorker(int num);
	/**
	 * �õ����ڵȴ�ִ�е���������
	 * @return
	 */
	int getJobSize();
}
