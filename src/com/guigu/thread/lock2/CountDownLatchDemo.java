package com.guigu.thread.lock2;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
	
	public static void main(String[] args) {
		new CountDownLatchDemo().init();
	}

	CountDownLatch latch = new CountDownLatch(3);
	Random r = new Random();

	public void init() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("子线程1正在工作");
				try {
					Thread.sleep(r.nextInt(5000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("子线程1完成工作");
				latch.countDown();
			}
		}, "子线程1").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("子线程2正在工作");
				try {
					Thread.sleep(r.nextInt(5000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("子线程2完成工作");
				latch.countDown();
			}
		}, "子线程2").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("子线程3正在工作");
				try {
					Thread.sleep(r.nextInt(5000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("子线程3完成工作");
				latch.countDown();
			}
		}, "子线程3").start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("主线程等待子线程全部执行完毕......");
				try {
					latch.await();// 使当前线程在锁存器倒计数至零之前一直等待，
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("所有的子线程工作完成....主线程开始工作....");
				
				
			}
		},"主线程").start();

	}
}
