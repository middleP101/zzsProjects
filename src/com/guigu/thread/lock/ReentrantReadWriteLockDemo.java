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
		//��������д�߳�д������
		for(int i=0;i<3;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true){
						ps.add(r.nextInt(1000));
					}
				}
			},"д�߳�"+i).start();
		}
		
	  //����������̶߳�ȡ����
		for(int i=0;i<5;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true){
						ps.get(r.nextInt(1000));
					}
				}
			},"���߳�"+i).start();
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

		// д�����������ǻ����
		public void add(Integer i) {
			try {
				rw.writeLock().lock();// ����д��
				System.out.println("׼��д������....");
				// ģ����ӵĹ���
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				list.add(i);
				System.out.println(Thread.currentThread().getName()+ "��׼��д������....");

			} finally {
				rw.writeLock().unlock();
			}

		}

		// ���� ������������ ������д���ǻ���
		public Integer get(Integer index) {
			Integer i = null;

			try {
				rw.readLock().lock();// ��������
				System.out.println(Thread.currentThread().getName()
						+ "��׼����ȡ����...");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				i=list.get(index);
				System.out.println(Thread.currentThread().getName()+"��ȡ�������...");
				
			} finally {
				rw.readLock().unlock();
			}
			return i;

		}

	}
}
