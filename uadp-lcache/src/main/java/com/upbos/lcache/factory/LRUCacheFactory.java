package com.upbos.lcache.factory;

import com.upbos.lcache.Cache;
import com.upbos.lcache.impl.LRUCacheImpl;

public class LRUCacheFactory implements CacheFactory{

	@Override
	public Cache createCache(String id, int maxsize, long timeout) {
		// TODO Auto-generated method stub
		return new LRUCacheImpl(id,maxsize,timeout);
	}

}