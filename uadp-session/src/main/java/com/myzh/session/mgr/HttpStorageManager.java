package com.myzh.session.mgr;

import com.myzh.session.session.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.myzh.lcache.Cache;
import com.myzh.lcache.CacheManager;
import com.myzh.session.SessionStorageManager;
import com.myzh.session.util.HttpRequestUtils;

public class HttpStorageManager implements SessionStorageManager {
	
	private static Logger logger = LogManager.getLogger(HttpStorageManager.class);
	
	private String ssoCenterUrl = "";
	
	private Boolean onLocalCache = true;
	
	private String localCacheName = "session_storage";
	
	private Cache localCache;
	
	public HttpStorageManager() {
		localCache = CacheManager.createCache(localCacheName, 100000, 1000*60);
	}
	
	public void setSsoCenterUrl(String ssoCenterUrl) {
		this.ssoCenterUrl = ssoCenterUrl;
	}
	
	@Override
	public void saveSession(Session token) {
		throw new RuntimeException("http方式不支持session保持操作");
	}

	@Override
	public void removeSession(String tokenId) {
		throw new RuntimeException("http方式不支持session删除操作");
	}

	@Override
	public void removeSession(String key, boolean isPrecise) {
		throw new RuntimeException("http方式不支持session删除操作");
	}

	@Override
	public Session getSession(String tokenId) {
		if(tokenId == null) {
			return null;
		}
		
		String value = null;
		if(this.onLocalCache) {
			value = (String)localCache.get(tokenId);
		}
		
		if(value == null) {
			long s = System.currentTimeMillis();
			value = HttpRequestUtils.doGet(this.ssoCenterUrl, "tokenId=" + tokenId);
			long e = System.currentTimeMillis();
			logger.debug("读取http缓存耗时{}毫秒, tokenId:{}", e - s, tokenId);
			if(this.onLocalCache) {
				localCache.put(tokenId, value);
			}
		}else {
			logger.debug("以http方式读取session时，命中本地缓存！");
		}
		
		if(value == null) {
			return null;
		}
		Session token = JSON.parseObject(value, Session.class);
		return token;

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
