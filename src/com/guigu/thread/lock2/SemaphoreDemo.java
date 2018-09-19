package com.guigu.thread.lock2;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {	
	
	public static void main(String[] args) {
		new SemaphoreDemo().init();
	}
	
	private DoSth ds =new DoSth();
	public void init() {
		final Semaphore semaphore = new Semaphore(3);
		for (int i = 0; i < 10; i++) {//10���߳�ͬʱ����DoSth��work����   
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						semaphore.acquire();// ������
						ds.work();
						semaphore.release();//�ͷ����
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			},"�߳�"+i).start();
		}

	}

	class DoSth {

		public void work() {//������������߳�ͬʱ����
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("��ǰ�̵߳�����:" + Thread.currentThread().getName());

		}
	}
}
