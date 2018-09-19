package com.guigu.thread.lock2;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerDemo {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final Exchanger exchanger = new Exchanger();

		executorService.execute(new Runnable() {
			@Override
			public void run() {

				try {
					String data1 = "杭州归谷AAAAAAAAAA";
					System.out.println("线程" + Thread.currentThread().getName()
							+ "正在把数据" + data1 + "交换出去");
					Thread.sleep((long) (Math.random() * 10000));
					String data2 = (String) exchanger.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName()
							+ "换回的数据是:" + data2);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});

		executorService.execute(new Runnable() {
			@Override
			public void run() {

				try {
					String data1 = "归谷杭州BBBBBBBBBBBBBBB";
					System.out.println("线程" + Thread.currentThread().getName()
							+ "正在把数据" + data1 + "交换出去");
					Thread.sleep((long) (Math.random() * 10000));
					String data2 = (String) exchanger.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName()
							+ "换回的数据是:" + data2);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});

	}

}
