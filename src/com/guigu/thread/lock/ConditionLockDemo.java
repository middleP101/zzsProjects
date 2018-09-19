package com.guigu.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionLockDemo {

	public static void main(String[] args) {
		new ConditionLockDemo().init();
	}

	private PrintString ps = new PrintString();

	public void init() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					ps.f1();
				}
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					ps.f2();
				}
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					ps.f3();
				}
			}
		}).start();

	}

	class PrintString {
		Lock lock = new ReentrantLock();
		// 根据lock锁 获得三个条件锁
		Condition c1 = lock.newCondition();
		Condition c2 = lock.newCondition();
		Condition c3 = lock.newCondition();
		int token = 1;

		public void f1() {
			lock.lock();
			try {
				while (token != 1) {
					try {
						c1.await();
					} catch (Exception e) {

					}
				}
				System.out.println(Thread.currentThread().getName());
				token = 2;
				// 单独唤醒下一个线程
				c2.signal();
			} finally {
				lock.unlock();
			}

		}

		public void f2() {
			lock.lock();
			try {
				while (token != 2) {
					try {
						c2.await();
					} catch (Exception e) {

					}
				}
				System.out.println(Thread.currentThread().getName());
				token = 3;
				// 单独唤醒下一个线程
				c3.signal();
			} finally {
				lock.unlock();
			}

		}

		public void f3() {
			lock.lock();
			try {
				while (token != 3) {
					try {
						c3.await();
					} catch (Exception e) {

					}
				}
				System.out.println(Thread.currentThread().getName());
				token = 1;
				// 单独唤醒下一个线程
				c1.signal();
			} finally {
				lock.unlock();
			}

		}

	}
}
