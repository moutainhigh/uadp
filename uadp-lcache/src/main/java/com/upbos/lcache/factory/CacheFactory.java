package com.upbos.lcache.factory;

import com.upbos.lcache.Cache;

public interface CacheFactory {
	Cache createCache(String id,int maxsize,long timeout);
}