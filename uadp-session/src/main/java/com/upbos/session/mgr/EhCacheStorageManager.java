package com.upbos.session.mgr;


import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import com.upbos.session.SessionStorageManager;
import com.upbos.session.session.Session;

public class EhCacheStorageManager implements SessionStorageManager {

	private CacheManager cacheMgr;

	private String storageIdPrefix = "session_";
	
	public void setStorageIdPrefix(String storageIdPrefix) {
		this.storageIdPrefix = storageIdPrefix;
	}
	
	private String toStorageId(String tokenId) {
		return this.storageIdPrefix + tokenId;
	}
	
	public void setCacheMgr(CacheManager cacheMgr) {
		this.cacheMgr = cacheMgr;
	}
	
	@Override
	public void saveSession(Session token) {
		if(token == null) {
			throw new RuntimeException("token对象不能为空。");
		}
		
		String tokenId = token.getId();
		Cache cache = cacheMgr.getCache("sessionCache");
		cache.put(toStorageId(tokenId), token);
	}

	@Override
	public void removeSession(String tokenId) {
		Cache cache = cacheMgr.getCache("sessionCache");
		cache.evict(toStorageId(tokenId));
	}

	@Override
	public void removeSession(String key, boolean isPrecise) {
		removeSession(key);
	}

	@Override
	public Session getSession(String tokenId) {
		if(tokenId == null) {
			return null;
		}
		Cache cache = cacheMgr.getCache("sessionCache");
		return cache.get(toStorageId(tokenId), Session.class);
	}

	@Override
	public void setOnLocalCache(Boolean on) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean getOnLocalCache() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
