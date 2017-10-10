package com.myzh.cache;

import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Collection;
import java.util.Set;

/**
 * Created by chensg on 2016/6/2.
 */
public interface ZSetCacheService<K, V> {

    Long intersectAndStoreForZSet(K key, K otherKey, K destKey);

    Long intersectAndStoreForZSet(K key, Collection<K> otherKeys, K destKey);

    Long unionAndStoreForZSet(K key, K otherKey, K destKey);

    Long unionAndStoreForZSet(K key, Collection<K> otherKeys, K destKey);

    Set<V> rangeForZSet(K key, long start, long end);

    Set<V> reverseRangeForZSet(K key, long start, long end);

    Set<V> rangeByScoreForZSet(K key, double min, double max);

    Set<V> rangeByScoreForZSet(K key, double min, double max, long offset, long count);

    Set<V> reverseRangeByScoreForZSet(K key, double min, double max);

    Set<V> reverseRangeByScoreForZSet(K key, double min, double max, long offset, long count);

    Boolean addForZSet(K key, V value, double score);

    Double incrementScoreForZSet(K key, V value, double delta);

    Long rankForZSet(K key, Object o);

    Long reverseRankForZSet(K key, Object o);

    Double scoreForZSet(K key, Object o);

    Long removeForZSet(K key, V... values);

    Long removeRangeForZSet(K key, long start, long end);

    Long removeRangeByScoreForZSet(K key, double min, double max);

    Long countForZSet(K key, double min, double max);

    Long sizeForZSet(K key);

    Long zCardForZSet(K key);
}
