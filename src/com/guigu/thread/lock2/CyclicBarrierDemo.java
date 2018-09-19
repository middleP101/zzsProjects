package com.guigu.thread.lock2;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {

	public static void main(String[] args) {
		final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
		// ������ ��ͬ���5������ Ҫ��:����������ͬʱ���ͬһ������ ����ִ����һ������
		ExecutorService executorService = Executors.newCachedThreadPool();

		for (int i = 0; i < 3; i++) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 5; i++) {
						try {
							Thread.sleep(new Random().nextInt(5000));
							System.out.println(Thread.currentThread().getName()
									+ "�ѵ��Ｏ�ϵ�" + (i + 1) + "���ڹ���"
									+ (cyclicBarrier.getNumberWaiting() + 1)
									+ "���̴߳ﵽ");
							if (cyclicBarrier.getNumberWaiting() + 1 == 3) {
								System.out.println("���е��̵߳��Ｏ�ϵ�,������һ������");
							} else {
								System.out.println("�ȴ������̵߳Ĵﵽ...");
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
