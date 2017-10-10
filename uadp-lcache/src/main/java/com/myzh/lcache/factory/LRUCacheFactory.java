package com.myzh.lcache.factory;

import com.myzh.lcache.Cache;
import com.myzh.lcache.impl.LRUCacheImpl;

public class LRUCacheFactory implements CacheFactory{

	@Override
	public Cache createCache(String id, int maxsize, long timeout) {
		// TODO Auto-generated method stub
		return new LRUCacheImpl(id,maxsize,timeout);
	}

}