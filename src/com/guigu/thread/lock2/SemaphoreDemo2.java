package com.guigu.thread.lock2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo2 {

	public static void main(String[] args) {
		// �����̳߳� ��ʼ��10���߳�
		ExecutorService executorService = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(3);

		for (int i = 0; i < 10; i++) {
			Runnable runnable = new Runnable() {

				@Override
				public void run() {
					try {
						semaphore.acquire();//������
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
					}
					System.out.println("�߳�" + Thread.currentThread().getName()
							+ "����,��ǰ����" + (3-semaphore.availablePermits()) + "������");//����ǰ�ж��ٲ���
					//ģ��ִ�е�ʱ��
					try {
						Thread.sleep((long)(Math.random()*1000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					System.out.println("�߳�"+Thread.currentThread().getName()+"�����뿪");
					semaphore.release();//�Ƿ����
					System.out.println("�߳�" + Thread.currentThread().getName()
							+ "���뿪,��ǰ����" +(3- semaphore.availablePermits()) + "������");//����ǰ�ж��ٲ���

				}
			};
			//���̳߳��е�10���߳�ִ���������
			executorService.execute(runnable);
			
		}
		
		executorService.shutdown();

	}
}
