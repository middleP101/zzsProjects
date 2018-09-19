package com.guigu.thread.lock;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {

	public static void main(String[] args) {
		// 创建一个固定大小的线程池
		// 在线程池中开启了6个线程
		ExecutorService pool = Executors.newFixedThreadPool(10);
		// 在线程池中 创建10个线程
		ExecutorService pool2 = Executors.newCachedThreadPool();
		// 在线程池中 创建1个线程
		ExecutorService pool3 = Executors.newSingleThreadExecutor();

		// 开启十个任务 每个任务循环打印10次
		final Random r = new Random();

		for (int i = 0; i < 10; i++) {// 开启10个任务
			final int task = i;

			pool3.execute(new Runnable() {

				@Override
				public void run() {
					for (int k = 0; k < 10; k++) {// 每个任务循环打印10次
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("当前线程的名字:"
								+ Thread.currentThread().getName() + "运行第:"
								+ task + "个任务");
					}
				}
			});

		}
		// 关闭线程池
		pool.shutdown();

	}
}
