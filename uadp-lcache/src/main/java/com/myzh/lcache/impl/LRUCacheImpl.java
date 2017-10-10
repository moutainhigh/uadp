package com.myzh.lcache.impl;

import java.util.Map;
import java.util.UUID;

import com.myzh.lcache.Cache;
import com.myzh.lcache.entity.CacheEntry;
import com.myzh.lcache.entity.DefaultCacheEntry;

public class LRUCacheImpl implements Cache {
	private long timeout=0; //0代表永不超时
	private Map<Object,CacheEntry> cache=null;
	private String id;
	public LRUCacheImpl(String id,int maxsize,long timeout){
		this.timeout=timeout;
		this.cache=new LRULinkedHashMap(maxsize);
		this.id=id;
	}
	public LRUCacheImpl(int maxsize,long timeout){
		this.timeout=timeout;
		this.cache=new LRULinkedHashMap(maxsize);
		this.id=UUID.randomUUID().toString();
	}

	@Override
	public String getId() {
		return this.id;
	}


	@Override
	public Object get(String key) {
		Object value=null;
		CacheEntry entry;
		synchronized (this) {
			entry=cache.get(key);
		}
		if(entry!=null){
			//如果没有超时，或者超时设置为0
			if((System.currentTimeMillis()-entry.getTimeCached())<timeout||timeout==0){
				value=entry.getValue();
			}else{
				cache.remove(key);
			}
		}
		return value;
	}


	@Override
	public synchronized void put(String key, Object value) {
		cache.put(key, new DefaultCacheEntry(value, System.currentTimeMillis()));
	}

	@Override
	public synchronized void clear() {
		cache.clear();
	}


	@Override
	public synchronized void remove(String key) {
		cache.remove(key);
	}

	@Override
	public int size() {
		return cache.size();
	}
}