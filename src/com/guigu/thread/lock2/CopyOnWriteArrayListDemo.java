package com.guigu.thread.lock2;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {
	
	public static void main(String[] args) {
		new CopyOnWriteArrayListDemo().init();
	}

	private DoSth ds = new DoSth();

	public void init() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				ds.work("1");
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				ds.work("1");
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				ds.work("2");
			}
		}).start();

	}

	class DoSth {
//		ArrayList<String> list = new ArrayList<String>();
		CopyOnWriteArrayList<String> list =new CopyOnWriteArrayList<String>();
		
		public void work(String value) {
			if (!list.contains(value)) {
				// 对传递过来的value添加到list集合中
				list.add(value);
			} else {
				// 如果添加的数据已经在集合中包含 则对该集合进行迭代遍历
				for (String s : list) {

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					System.out.println(s);

				}
			}

		}

	}
}
