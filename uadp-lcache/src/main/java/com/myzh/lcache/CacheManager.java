package com.myzh.lcache;

import java.util.concurrent.ConcurrentHashMap;

import com.myzh.lcache.factory.CacheFactory;
import com.myzh.lcache.factory.LRUCacheFactory;

public class CacheManager {
	private static final ConcurrentHashMap<String, Cache> caches = new ConcurrentHashMap<>();

	/**
	 * 缓存策略枚举
	 */
	public static enum CacheStrategy {
		LRU
	}

	public static Cache createCache(String id, int maxsize, long timeout) {
		return createCache(id, maxsize, timeout, null);
	}
	
	/**
	 * 
	 * @param maxsize
	 * @param timeout
	 * @param strategy
	 *            可为null。默认LRU
	 * @return
	 */
	public static Cache createCache(String id, int maxsize, long timeout, CacheStrategy strategy) {
		if (strategy == null) {
			strategy = CacheStrategy.LRU;
		}
		Cache cache = getCacheFactory(strategy).createCache(id, maxsize, timeout);
		caches.putIfAbsent(cache.getId(), cache);
		return cache;
	}

	private static CacheFactory getCacheFactory(CacheStrategy strategy) {
		CacheFactory factory = null;
		if ("LRU".equals(strategy.name())) {
			factory = new LRUCacheFactory();
		}
		return factory;
	}

	public static Cache getCacheById(String cacheId) {
		return caches.get(cacheId);
	}

	public static void clear() {
		caches.clear();
	}

	public static void remove(String cacheId) {
		caches.remove(cacheId);
	}

}