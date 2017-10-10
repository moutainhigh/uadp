package com.upbos.cache;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by chensg on 2016/6/2.
 */
public interface ListCacheService<K, V> {

    List<V> rangeForList(K key, long start, long end);

    void trimForList(K key, long start, long end);

    Long sizeForList(K key);

    Long leftPushForList(K key, V value);

    Long listPushAllForList(K key, V... values);

    Long leftPushAllForList(K key, Collection<V> values);

    Long leftPushIfPresentForList(K key, V value);

    Long leftPushForList(K key, V pivot, V value);

    Long rightPushForList(K key, V value);

    Long rightPushAllForList(K key, V... values);

    /**
     * Insert all {@literal values} at the tail of the list stored at {@literal key}.
     *
     */
    Long rightPushAllForList(K key, Collection<V> values);

    Long rightPushIfPresentForList(K key, V value);

    Long rightPushForList(K key, V pivot, V value);

    void setForList(K key, long index, V value);

    Long removeForList(K key, long i, V value);

    V indexForList(K key, long index);

    V leftPopForList(K key);

    V leftPopForList(K key, long timeout, TimeUnit unit);

    V rightPopForList(K key);

    V rightPopForList(K key, long timeout, TimeUnit unit);

    V rightPopAndLeftPushForList(K sourceKey, K destinationKey);

    V rightPopAndLeftPushForList(K sourceKey, K destinationKey, long timeout, TimeUnit unit);
}
