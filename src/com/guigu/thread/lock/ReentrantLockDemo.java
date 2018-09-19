package com.guigu.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
	
	public static void main(String[] args) {
		new ReentrantLockDemo().init();
	}

	private PrintString ps = new PrintString();

	public void init() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					ps.showString("AAAAAAAAAAAAAAAAAAAAAAAAAA");
				}
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					ps.showString("BBBBBBBBBBBBBBBBBBBBBBBBBB");
				}
			}
		}).start();
	}

	class PrintString {
		Lock lock = new ReentrantLock(true);

		public  void showString(String str) {
			try {
				lock.lock();// 手动加锁

				for (int i = 0; i < str.length(); i++) {
					System.out.print(str.charAt(i));
				}
				System.out.println();

			} catch (Exception e) {

			} finally {
				lock.unlock();// 释放锁
			}

		}

	}
}
