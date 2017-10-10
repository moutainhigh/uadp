package com.myzh.session.mgr;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.myzh.session.session.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.myzh.cache.CacheService;
import com.myzh.lcache.Cache;
import com.myzh.lcache.CacheManager;
import com.myzh.session.SessionStorageManager;

public class RedisStorageManager implements SessionStorageManager {
	
	private static Logger logger = LogManager.getLogger(RedisStorageManager.class);
	
	private Boolean onLocalCache = true;
	
	private int sessionTimeout = 60;  //单位为分
	
	private String storageIdPrefix = "session_";
	
	private String localCacheName = "session_storage";
	
	private Cache localCache;
	
	public RedisStorageManager() {
		localCache = CacheManager.createCache(localCacheName, 100000, 1000*60);
	}
	
	public void setSessionTimeout(int sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}
	
	public void setStorageIdPrefix(String storageIdPrefix) {
		this.storageIdPrefix = storageIdPrefix;
	}
	
	private String toStorageId(String tokenId) {
		return this.storageIdPrefix + tokenId;
	}
	
	private CacheService<String, Object> cacheService;
	
	public void setCacheService(CacheService<String, Object> cacheService) {
		this.cacheService = cacheService;
	}
	
	@Override
	public void saveSession(Session token) {
		if(token == null) {
			throw new RuntimeException("token对象不能为空。");
		}
		
		String tokenId = token.getId();
		
		if(tokenId != null) {
//			String value = JSON.toJSONString(session);
			String storageId = toStorageId(tokenId);
			cacheService.set(storageId, token, this.sessionTimeout, TimeUnit.MINUTES);
			if(this.onLocalCache) {
				localCache.put(storageId, token);
			}
			
		}else {
			logger.error("session存储redis方式，tokenId为空。");
		}
	}

	@Override
	public void removeSession(String tokenId) {
		String key = toStorageId(tokenId);
		cacheService.delete(key);
		if(this.onLocalCache) {
			localCache.remove(key);
		}
	}

	@Override
	public void removeSession(String key, boolean isPrecise) {
		if (isPrecise) {
			removeSession(key);
		} else {
			Set<String> keys = cacheService.keys(toStorageId(key + "*"));
			cacheService.delete(keys);
			// 删除本地缓存
			if(this.onLocalCache && keys != null) {
				Iterator<String> keyIt = keys.iterator();
				while(keyIt.hasNext()) {
					localCache.remove(keyIt.next());
				}
			}
		}
	}

	@Override
	public Session getSession(String tokenId) {
		if(tokenId == null) {
			return null;
		}
		String storageId = toStorageId(tokenId);
		Object value = null;
		if(this.onLocalCache) {
			value = localCache.get(storageId);
		}
		
		if(value == null) {
			long s = System.currentTimeMillis();
			value = cacheService.get(storageId);
			cacheService.expire(storageId, this.sessionTimeout, TimeUnit.MINUTES);
			long e = System.currentTimeMillis();
			logger.debug("读取redis缓存耗时{}毫秒, tokenId:{}", e - s, tokenId);
			if(this.onLocalCache) {
				localCache.put(storageId, value);
			}
		}else {
			logger.debug("以redis方式读取session时，命中本地缓存！");
		}
		
		if(value == null) {
			return null;
		}
//		Session session = JSON.parseObject((String)value, Session.class);
//		return session;
		return (Session) value;
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
