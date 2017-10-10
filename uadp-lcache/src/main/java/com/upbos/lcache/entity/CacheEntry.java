package com.upbos.lcache.entity;

public interface CacheEntry {
	/**
	 * 加入缓存时的时间
	 * @return
	 */
	long getTimeCached();
	Object getValue();
}