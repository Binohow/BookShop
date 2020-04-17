package com.icss.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	
	public static void main(String[] args) {		
		ExecutorService pool =  Executors.newFixedThreadPool(100);
		for(int i=0;i<1000;i++) {
			pool.submit(new Runnable() {
				
				@Override
				public void run() {
					
					Log.logger.info( OrderUtil.createNewOrderNo());
				}
			});
		}	
		pool.shutdown();
	}

}
