package com.hike.cache.action;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.hike.cache.helper.ExpiryCache;

public class ExpiryCacheAction<K,V> implements ExpiryCache<K, V>{
	
	private Map<K,V> cacheMap;
	
	public ExpiryCacheAction() {
		this.cacheMap = new HashMap<>();
	}

	@Override
	public void put(final K key, V value, final long ttl, final TimeUnit timeUnit) {
		if(key == null) {
			throw new IllegalArgumentException("Invalid Key");
		}
		
		synchronized (cacheMap) {
			cacheMap.put(key, value);
		}
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try{
					Thread.sleep(timeUnit.toMillis(ttl));
				}catch(InterruptedException e){
				}
				cleanUp(key);
			}
		});
		
		t.start();
	}

	private void cleanUp(K key) {
		synchronized (cacheMap) {
			cacheMap.remove(key);
		}
	}


	@Override
	public V get(K key) {
		if(key == null) {
			throw new IllegalArgumentException("Invalid Key");
		}
		synchronized (cacheMap) {
			return this.cacheMap.get(key);
		}	
	}
	
	public void printAll() {
		synchronized (cacheMap) {
			for(Map.Entry<K, V> entry : cacheMap.entrySet()) {
				System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
			}
		}
		
	}
	
}
