package com.lyt.thread.status;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ����ʱ
 * 
 * @author Administrator
 *
 */
public class SleepThread {
	public static void main(String[] args) throws InterruptedException {
		Date endTime = new Date(System.currentTimeMillis() + 10*1000);//��ǰʱ���ʮ��
		long end = endTime.getTime();
		while(true){
			//parse(source)��ת��Ϊʱ������
			//format(endTime)��ת��Ϊ�ַ���
			//�����
			System.out.println(new SimpleDateFormat("mm:ss").format(endTime));//��ӡ���Ӻ��� MM ���·�
			//������һ���ʱ��
			Thread.sleep(1000);
			endTime = new Date(endTime.getTime() - 1000);
			//10������ ����  ���� �˳�
			if(end - 10000 > endTime.getTime()){
				break;
			}
			
		}
	}
}
