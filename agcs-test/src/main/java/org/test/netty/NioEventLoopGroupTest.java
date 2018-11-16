package org.test.netty;

import java.util.concurrent.ThreadFactory;

import io.netty.channel.nio.NioEventLoopGroup;

public class NioEventLoopGroupTest {
	
	public static void main(String[] args) {
		eventLoopGroup();
	}
	
	public static void eventLoopGroup(){
		NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup(4, new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				System.out.println(Thread.currentThread().getName());
				return new Thread(r, "NettyServerWorkerThread");
			}
		});
		
		eventLoopGroup.execute(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("fdsfdsf");
				
			}
		});
	}

}
