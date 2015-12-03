package com.hike.cache.helper;

import java.util.concurrent.TimeUnit;

public interface ExpiryCache<K,V> {
	public void put(K key, V value, long ttl, TimeUnit timeUnit);
	public V get(K key);

}
