package com.guigu.thread.lock2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo2 {

	public static void main(String[] args) {
		// 创建线程池 初始化10个线程
		ExecutorService executorService = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(3);

		for (int i = 0; i < 10; i++) {
			Runnable runnable = new Runnable() {

				@Override
				public void run() {
					try {
						semaphore.acquire();//获得许可
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
					}
					System.out.println("线程" + Thread.currentThread().getName()
							+ "进入,当前已有" + (3-semaphore.availablePermits()) + "个并发");//看当前有多少并发
					//模拟执行的时间
					try {
						Thread.sleep((long)(Math.random()*1000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					System.out.println("线程"+Thread.currentThread().getName()+"即将离开");
					semaphore.release();//是否许可
					System.out.println("线程" + Thread.currentThread().getName()
							+ "已离开,当前已有" +(3- semaphore.availablePermits()) + "个并发");//看当前有多少并发

				}
			};
			//让线程池中的10个线程执行这个任务
			executorService.execute(runnable);
			
		}
		
		executorService.shutdown();

	}
}
