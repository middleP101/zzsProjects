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
				System.out.println("���߳�1���ڹ���");
				try {
					Thread.sleep(r.nextInt(5000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("���߳�1��ɹ���");
				latch.countDown();
			}
		}, "���߳�1").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("���߳�2���ڹ���");
				try {
					Thread.sleep(r.nextInt(5000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("���߳�2��ɹ���");
				latch.countDown();
			}
		}, "���߳�2").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("���߳�3���ڹ���");
				try {
					Thread.sleep(r.nextInt(5000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("���߳�3��ɹ���");
				latch.countDown();
			}
		}, "���߳�3").start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("���̵߳ȴ����߳�ȫ��ִ�����......");
				try {
					latch.await();// ʹ��ǰ�߳�������������������֮ǰһֱ�ȴ���
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("���е����̹߳������....���߳̿�ʼ����....");
				
				
			}
		},"���߳�").start();

	}
}
