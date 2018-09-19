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
					String data1 = "���ݹ��AAAAAAAAAA";
					System.out.println("�߳�" + Thread.currentThread().getName()
							+ "���ڰ�����" + data1 + "������ȥ");
					Thread.sleep((long) (Math.random() * 10000));
					String data2 = (String) exchanger.exchange(data1);
					System.out.println("�߳�" + Thread.currentThread().getName()
							+ "���ص�������:" + data2);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});

		executorService.execute(new Runnable() {
			@Override
			public void run() {

				try {
					String data1 = "��Ⱥ���BBBBBBBBBBBBBBB";
					System.out.println("�߳�" + Thread.currentThread().getName()
							+ "���ڰ�����" + data1 + "������ȥ");
					Thread.sleep((long) (Math.random() * 10000));
					String data2 = (String) exchanger.exchange(data1);
					System.out.println("�߳�" + Thread.currentThread().getName()
							+ "���ص�������:" + data2);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});

	}

}
