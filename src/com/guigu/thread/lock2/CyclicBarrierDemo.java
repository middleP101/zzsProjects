package com.guigu.thread.lock2;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {

	public static void main(String[] args) {
		final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
		// 三个人 共同完成5个任务 要求:必须三个人同时完成同一个任务 才能执行下一个任务
		ExecutorService executorService = Executors.newCachedThreadPool();

		for (int i = 0; i < 3; i++) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 5; i++) {
						try {
							Thread.sleep(new Random().nextInt(5000));
							System.out.println(Thread.currentThread().getName()
									+ "已到达集合点" + (i + 1) + "现在共有"
									+ (cyclicBarrier.getNumberWaiting() + 1)
									+ "个线程达到");
							if (cyclicBarrier.getNumberWaiting() + 1 == 3) {
								System.out.println("所有的线程到达集合点,进行下一个任务");
							} else {
								System.out.println("等待其他线程的达到...");
							}

							cyclicBarrier.await();

						} catch (Exception e) {
							e.printStackTrace();
						}

					}

				}
			});
		}
		executorService.shutdown();

	}
}
