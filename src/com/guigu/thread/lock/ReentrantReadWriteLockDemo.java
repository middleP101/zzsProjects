package com.guigu.thread.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo {
	
	public static void main(String[] args) {
		new ReentrantReadWriteLockDemo().init();
	}
	
	private PrintShow ps =new PrintShow();
	Random r =new Random();
	public void init(){
		//开启三个写线程写入数据
		for(int i=0;i<3;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true){
						ps.add(r.nextInt(1000));
					}
				}
			},"写线程"+i).start();
		}
		
	  //开启五个读线程读取数据
		for(int i=0;i<5;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true){
						ps.get(r.nextInt(1000));
					}
				}
			},"读线程"+i).start();
		}
		
		
	}
	
	

	class PrintShow {
		List<Integer> list = new ArrayList<Integer>();
		ReadWriteLock rw = new ReentrantReadWriteLock();

		public PrintShow() {
			for (int i = 0; i < 1000; i++) {
				list.add(i);
			}
		}

		// 写锁与其他锁是互斥的
		public void add(Integer i) {
			try {
				rw.writeLock().lock();// 开启写锁
				System.out.println("准备写入数据....");
				// 模拟添加的过程
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				list.add(i);
				System.out.println(Thread.currentThread().getName()+ "正准备写入数据....");

			} finally {
				rw.writeLock().unlock();
			}

		}

		// 读锁 与读锁不互斥的 读锁与写锁是互斥
		public Integer get(Integer index) {
			Integer i = null;

			try {
				rw.readLock().lock();// 开启读锁
				System.out.println(Thread.currentThread().getName()
						+ "正准备读取数据...");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				i=list.get(index);
				System.out.println(Thread.currentThread().getName()+"读取数据完成...");
				
			} finally {
				rw.readLock().unlock();
			}
			return i;

		}

	}
}
