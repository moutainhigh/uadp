package com.myzh.lcache;

public class Demo {
	
	
	public static void main(String[] args) {
		CacheManager.createCache("user", 1000, 100);
		CacheManager.createCache("student", 1000, 60);
		
		CacheManager.getCacheById("user").put("name", "wangjzd");
		try {
			Thread.sleep(60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("first:" + CacheManager.getCacheById("user").get("name"));
		
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("second:" + CacheManager.getCacheById("user").get("name"));
		
	}
}
