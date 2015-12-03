package com.hike.cache.test;

import java.util.concurrent.TimeUnit;

import com.hike.cache.action.ExpiryCacheAction;

public class ExpiryCacheActionTest {

	public static void main(String[] args) throws InterruptedException {
		ExpiryCacheAction<String, String> cache = new ExpiryCacheAction<>();
		cache.put("Flipkart", "Sachin Bansal", 5 , TimeUnit.SECONDS);
        cache.put("Amazon", "Bezos", 10 , TimeUnit.SECONDS);
        cache.put("Google", "Page", 15, TimeUnit.SECONDS);
        cache.put("Microsoft", "Bill Gates", 20, TimeUnit.SECONDS);
        
       cache.printAll();
       System.out.println("----------------------");
       
       Thread.sleep(TimeUnit.SECONDS.toMillis(10));
       
       cache.printAll();
       Thread.sleep(TimeUnit.SECONDS.toMillis(5));
       
       System.out.println("----------------------");
       
       cache.printAll();
       
       System.out.println("----------------------");
       
       Thread.sleep(TimeUnit.SECONDS.toMillis(5));
       
       cache.printAll();
       
       System.out.println("----------------------");
       
	}

}
