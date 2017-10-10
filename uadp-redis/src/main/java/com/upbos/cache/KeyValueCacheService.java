package com.upbos.cache;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by chensg on 2016/6/2.
 */
public interface KeyValueCacheService<K, V> {

    Boolean hasKey(K key);

    void delete(K key);

    void delete(Collection<K> keys);

    Boolean expire(K key, long expireTime, TimeUnit unit);

    Boolean expireAt(K key, Date expireTime);

    void set(K key, V value);

    void set(K key, V value, long expireTime, TimeUnit unit);

    void multiSet(Map<? extends K, ? extends V> map);

    V get(K key);

    V getAndSet(K key, V value);

    Set<K> keys(K key);

    List<V> multiGet(Collection<K> keys);

    Long increment(K key, long incrementValue);

    Double increment(K key, double incrementValue);
}
