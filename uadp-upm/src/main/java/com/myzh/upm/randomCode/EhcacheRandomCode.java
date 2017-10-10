package com.myzh.upm.randomCode;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

public class EhcacheRandomCode implements RandomCodeInterface {

	private CacheManager cacheMgr;
	
	public void setCacheMgr(CacheManager cacheMgr) {
		 this.cacheMgr = cacheMgr;
	}
	
	@Override
	public String getRandomCode(String key) {
		Cache cache = cacheMgr.getCache("sessionCache");
		return cache.get(key, String.class);
	}

	@Override
	public void setRandomCode(String key, String value) {
		Cache cache = cacheMgr.getCache("sessionCache");
		cache.put(key, value);
	}

}
