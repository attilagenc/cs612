package edu.herguan.cs612.termproject.loadgenerator;

import java.util.Date;

import edu.herguan.cs612.termproject.HttpClient;

public class LoadGenerator {

	HttpClient client = new HttpClient();

	public void generateLoad(final int threadCount, final long delay, final long finishAt, final String url){
		Thread[] threads = new Thread[threadCount];
		Runnable task = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Load generator thread: "+Thread.currentThread().getName()+" started time="+new Date(System.currentTimeMillis()));
				while(System.currentTimeMillis() < finishAt){
					try {
						long start = System.currentTimeMillis();
						client.get(url);
						long duration = System.currentTimeMillis()-start;
						if (delay-duration >0)
							Thread.sleep(delay-duration);
					} catch (Exception e) {
						e.printStackTrace();
						System.err.println("Error in thread: "+Thread.currentThread().getId()+" url:"+url);
					}
				}
				System.out.println("Load generator thread: "+Thread.currentThread().getName()+" completed. time="+new Date(System.currentTimeMillis()));
			}
		};
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(task,"Load Generator-"+i);
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
	}
}
