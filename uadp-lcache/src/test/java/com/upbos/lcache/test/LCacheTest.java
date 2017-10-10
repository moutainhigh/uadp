package com.upbos.lcache.test;

import com.upbos.lcache.Cache;
import com.upbos.lcache.CacheManager;

public class LCacheTest {
	
	public static void main(String[] args) {
		String cacheId = "session_storage";
		String uidPrefix = "session_";
		Cache c = CacheManager.createCache(cacheId, 5000, 1000*10);
		c.put("test", "sss");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(c.get("test"));
		
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(c.get("test"));
	}
}
