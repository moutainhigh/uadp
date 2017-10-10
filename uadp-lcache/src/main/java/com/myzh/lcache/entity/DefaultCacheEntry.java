package com.myzh.lcache.entity;

public class DefaultCacheEntry implements CacheEntry{
	private long timecached=-1;
	private Object value;
	public DefaultCacheEntry(Object value,long timecached){
		this.value=value;
		this.timecached=timecached;
	}

	@Override
	public long getTimeCached() {
		return timecached;
	}

	@Override
	public Object getValue() {
		return value;
	}

}