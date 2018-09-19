package com.guigu.thread.lock;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {

	public static void main(String[] args) {
		// ����һ���̶���С���̳߳�
		// ���̳߳��п�����6���߳�
		ExecutorService pool = Executors.newFixedThreadPool(10);
		// ���̳߳��� ����10���߳�
		ExecutorService pool2 = Executors.newCachedThreadPool();
		// ���̳߳��� ����1���߳�
		ExecutorService pool3 = Executors.newSingleThreadExecutor();

		// ����ʮ������ ÿ������ѭ����ӡ10��
		final Random r = new Random();

		for (int i = 0; i < 10; i++) {// ����10������
			final int task = i;

			pool3.execute(new Runnable() {

				@Override
				public void run() {
					for (int k = 0; k < 10; k++) {// ÿ������ѭ����ӡ10��
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("��ǰ�̵߳�����:"
								+ Thread.currentThread().getName() + "���е�:"
								+ task + "������");
					}
				}
			});

		}
		// �ر��̳߳�
		pool.shutdown();

	}
}
