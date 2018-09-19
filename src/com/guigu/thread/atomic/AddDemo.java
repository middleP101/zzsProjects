package com.guigu.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AddDemo {
	
	public int i=0;
	
	public static void main(String[] args) {
		new AddDemo().init();
	}
	
	public void init(){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				for(int i=0;i<10000;i++){
					increase();
				}
			}
		}).start();
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<10000;i++){
					increase();
				}
			}
		}).start();
		
		while(Thread.activeCount()!=1){
			
		}
		System.out.println(i);
		
	}
	
	private AtomicInteger ai =new AtomicInteger();
	
	public synchronized void  increase(){
//		++i;
		ai.getAndIncrement();
	}
	
}
