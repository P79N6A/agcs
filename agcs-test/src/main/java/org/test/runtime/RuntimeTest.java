package org.test.runtime;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class RuntimeTest {
	
	public static void main(String[] args) {
		
		Thread threa1 = new Thread(){
			public void run(){
				System.out.println("thread1 ....");
			}
		};
		Thread threa2 = new Thread(){
			public void run(){
				System.out.println("thread2 ....");
			}
		};
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				System.out.println("shutdown .....");
			}
		}, "Shutdown"));
		
		threa1.start();
		threa2.start();
		
		Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
			private final AtomicLong threadIndex = new AtomicLong(0);
			@Override
			public Thread newThread(Runnable r) {
				return new Thread(r, "Thread"+this.threadIndex.incrementAndGet());
			}
		}).scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
				
			}
		}, 5, 10, TimeUnit.SECONDS);
		System.out.println(Thread.currentThread().getName());
		
	}

}
