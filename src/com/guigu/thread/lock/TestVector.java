package com.guigu.thread.lock;

import java.util.Vector;
	
public class TestVector {

	private VectorOpr vo = new VectorOpr();
	
	public void init() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				vo.printVector();
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				vo.deleteVector();
			}
		}).start();
	}
	
	public static void main(String[] args) {
		new TestVector().init();
	}
	
	class VectorOpr {
		Vector<Integer> list = new Vector<Integer>();

		VectorOpr() {
			for (int i = 0; i < 10; i++) {
				list.add(i);
			}
		}

		public synchronized void printVector() {
			for (Integer i : list) {
				System.out.println(i);
			}
		}

		public synchronized void deleteVector() {
			for (int i = list.size() - 1; i >= 0; i--) {
				list.remove(i);
			}
		}

	}

}
