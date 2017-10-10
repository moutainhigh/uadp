package com.myzh.session.mgr;


import com.myzh.lcache.CacheManager;
import com.myzh.session.SessionStorageManager;
import com.myzh.session.session.Session;

public class CacheStorageManager implements SessionStorageManager {

	private String cacheId = "session_storage";
	private String uidPrefix = "session_";
	
	private Boolean onLocalCache;
	public CacheStorageManager() {
		CacheManager.createCache(cacheId, 5000, 1000*60*30);
	}
	
	private String toStorageId(String tokenId) {
		return this.uidPrefix + tokenId;
	}
	
	@Override
	public void saveSession(Session token) {
		if(token == null) {
			throw new RuntimeException("token对象不能为空。");
		}
		
		String tokenId = token.getId();
		CacheManager.getCacheById(cacheId).put(toStorageId(tokenId), token);
	}

	@Override
	public void removeSession(String tokenId) {
		CacheManager.getCacheById(cacheId).remove(toStorageId(tokenId));
	}

	@Override
	public void removeSession(String key, boolean isPrecise) {
		if (isPrecise) {
			removeSession(key);
		} else {
			removeSession(key);
		}
	}

	@Override
	public Session getSession(String tokenId) {
		return (Session)CacheManager.getCacheById(cacheId).get(toStorageId(tokenId));
	}

	@Override
	public void setOnLocalCache(Boolean on) {
		this.onLocalCache = on;
	}

	@Override
	public Boolean getOnLocalCache() {
		return this.onLocalCache;
	}

	
}
