package com.guigu.thread.lock2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo2 {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newCachedThreadPool();
		final CountDownLatch cdOrder = new CountDownLatch(1);
		final CountDownLatch cdAnswer = new CountDownLatch(3);

		for (int i = 0; i < 3; i++) {
			Runnable runnable = new Runnable() {

				@Override
				public void run() {
					try {
						System.out.println("�߳�"
								+ Thread.currentThread().getName() + "��׼����������");
						cdOrder.await();
						System.out.println("�߳�"
								+ Thread.currentThread().getName() + "�ѽ�������");
						Thread.sleep((long) Math.random() * 10000);
						System.out.println("�߳�"
								+ Thread.currentThread().getName()
								+ "��Ӧ�������...����������");
						cdAnswer.countDown();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			executorService.execute(runnable);
		}

		try {
			Thread.sleep((long) (Math.random() * 10000));
			System.out.println("�߳�" + Thread.currentThread().getName()
					+ "������������");
			cdOrder.countDown();
			System.out.println("�߳�" + Thread.currentThread().getName()
					+ "�Ѿ���������,���ڵȴ����....");
			cdAnswer.await();
			System.out.println("�߳�" + Thread.currentThread().getName()
					+ "�Ѿ��յ����еķ��ؽ��....");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		executorService.shutdown();

	}

}
