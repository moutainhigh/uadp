package com.upbos.lcache;

public interface Cache {
	/**
	 * 缓存ID
	 * @return
	 */
	String getId(); 
	Object get(String key);
	void put(String key,Object value);
	void clear();
	void remove(String key);
	int size();
}