package com.guigu.thread.lock2;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {	
	
	public static void main(String[] args) {
		new SemaphoreDemo().init();
	}
	
	private DoSth ds =new DoSth();
	public void init() {
		final Semaphore semaphore = new Semaphore(3);
		for (int i = 0; i < 10; i++) {//10个线程同时访问DoSth的work方法   
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						semaphore.acquire();// 获得许可
						ds.work();
						semaphore.release();//释放许可
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			},"线程"+i).start();
		}

	}

	class DoSth {

		public void work() {//最多允许三个线程同时访问
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("当前线程的名字:" + Thread.currentThread().getName());

		}
	}
}
