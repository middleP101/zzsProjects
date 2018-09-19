package com.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadDemo {

	private static int j=0;
	public static void main(String[] args) {
		ThreadLocal<String> threadLocal = new ThreadLocal<String>();
		ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		System.out.println(Runtime.getRuntime().availableProcessors());
		
		for (int i = 0; i < 10 ; i++) {
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					j++;
					System.out.print(Thread.currentThread().isAlive() + "-");
					System.out.println(String.valueOf(Thread.currentThread().getId()) + "-" + j);
				}
			});
		}
		executor.shutdown();
	}
}
