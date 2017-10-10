package com.myzh.lcache.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import com.myzh.lcache.entity.CacheEntry;

public class LRULinkedHashMap extends LinkedHashMap<Object, CacheEntry>{
	private int maxSize;
	public LRULinkedHashMap(int maxsize){
		super(maxsize/2+1,0.75f,true);
		this.maxSize=maxsize;
	}
	/* 
	 * 当缓存数超过最大容量时，返回true把最后一个移除。 
	 * @see java.util.LinkedHashMap#removeEldestEntry(java.util.Map.Entry)
	 */
	@Override
	 protected boolean removeEldestEntry(Map.Entry eldest) {
        return size()>maxSize;
    }
}
