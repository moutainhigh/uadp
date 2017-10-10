package com.myzh.lcache.factory;

import com.myzh.lcache.Cache;

public interface CacheFactory {
	Cache createCache(String id,int maxsize,long timeout);
}