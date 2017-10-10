package com.upbos.cache.redis;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;

import com.upbos.cache.CacheService;

/**
 * Created by chensg on 2016/5/26.
 */
public class RedisCacheUseSpringServiceImpl<K,V> implements CacheService<K,V> {
    @Resource
    private RedisTemplate<K, V> redisTemplate;

    public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {
    	this.redisTemplate = redisTemplate;
    }
    @Override
    public Boolean hasKey(K key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void delete(K key) {
        redisTemplate.delete(key);
    }

    @Override
    public void delete(Collection keys) {
        redisTemplate.delete(keys);
    }

    @Override
    public Boolean expire(K key, long expireTime, TimeUnit unit) {
        return redisTemplate.expire(key, expireTime, unit);
    }

    @Override
    public Boolean expireAt(K key, Date expireTime) {
        return redisTemplate.expireAt(key, expireTime);
    }

    @Override
    public void set(K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(K key, V value, long expireTime, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, expireTime, unit);
    }

    @Override
    public void multiSet(Map map) {
        redisTemplate.opsForValue().multiSet(map);
    }

    @Override
    public V get(Object key) {
        return redisTemplate.opsForValue().get(key);
    }
    @Override
    public Set<K> keys(K key) {
        return redisTemplate.keys(key);
    }

    @Override
    public V getAndSet(K key, V value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }

    @Override
    public List<V> multiGet(Collection keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    @Override
    public Long increment(K key, long incrementValue) {
        return redisTemplate.opsForValue().increment(key, incrementValue);
    }

    @Override
    public Double increment(K key, double incrementValue) {
        return redisTemplate.opsForValue().increment(key, incrementValue);
    }

    @Override
    public List<V> rangeForList(K key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    @Override
    public void trimForList(K key, long start, long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }

    @Override
    public Long sizeForList(K key) {
        return redisTemplate.opsForList().size(key);
    }

    @Override
    public Long leftPushForList(K key, V value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    public Long listPushAllForList(K key, V... values) {
        return redisTemplate.opsForList().leftPushAll(key, values);
    }

    @Override
    public Long leftPushIfPresentForList(K key, V value) {
        return redisTemplate.opsForList().leftPushIfPresent(key, value);
    }

    @Override
    public Long leftPushForList(K key, V pivot, V value) {
        return redisTemplate.opsForList().leftPush(key, pivot, value);
    }

    @Override
    public Long rightPushForList(K key, V value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    @Override
    public Long rightPushAllForList(K key, V... values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    @Override
    public Long rightPushIfPresentForList(Object key, Object value) {
        return null;
    }

    @Override
    public Long rightPushForList(K key, V pivot, V value) {
        return redisTemplate.opsForList().rightPush(key, pivot, value);
    }

    @Override
    public void setForList(K key, long index, V value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    @Override
    public Long removeForList(K key, long i, V value) {
        return redisTemplate.opsForList().remove(key, i, value);
    }

    @Override
    public V indexForList(K key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    @Override
    public V leftPopForList(K key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public V leftPopForList(K key, long timeout, TimeUnit unit) {
        return redisTemplate.opsForList().leftPop(key,timeout, unit);
    }
    @Override
    public Long leftPushAllForList(K key, Collection<V> values) {
        return redisTemplate.opsForList().leftPushAll(key, values);
    }

    @Override
    public Long rightPushAllForList(K key, Collection<V> values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }
    @Override
    public V rightPopForList(K key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    @Override
    public V rightPopForList(K key, long timeout, TimeUnit unit) {
        return redisTemplate.opsForList().rightPop(key, timeout, unit);
    }

    @Override
    public V rightPopAndLeftPushForList(K sourceKey, K destinationKey) {
        return redisTemplate.opsForList().rightPopAndLeftPush(sourceKey, destinationKey);
    }

    @Override
    public V rightPopAndLeftPushForList(K sourceKey, K destinationKey, long timeout, TimeUnit unit) {
        return redisTemplate.opsForList().rightPopAndLeftPush(sourceKey, destinationKey, timeout, unit);
    }

    @Override
    public Set differenceForSet(K setKey1, K setKey2) {
        return redisTemplate.opsForSet().difference(setKey1, setKey2);
    }

    @Override
    public Set differenceForSet(K setKey, Collection<K> anotherSet) {
        return redisTemplate.opsForSet().difference(setKey, anotherSet);
    }

    @Override
    public Long differenceAndStoreForSet(K setKey1, K setKey2, K newSetKey) {
        return redisTemplate.opsForSet().differenceAndStore(setKey1, setKey2, newSetKey);
    }

    @Override
    public Set intersectForSet(K setKey1, K setKey2) {
        return redisTemplate.opsForSet().intersect(setKey1, setKey2);
    }

    @Override
    public Set intersectForSet(K setKey, Collection<K> anotherSet) {
        return redisTemplate.opsForSet().intersect(setKey, anotherSet);
    }

    @Override
    public Long intersectAndStoreForSet(K setKey1, K setKey2, K newSetKey) {
        return redisTemplate.opsForSet().intersectAndStore(setKey1, setKey2, newSetKey);
    }

    @Override
    public Long intersectAndStoreForSet(K setKey, Collection<K> anotherSet, K newSetKey) {
        return redisTemplate.opsForSet().intersectAndStore(setKey, anotherSet, newSetKey);
    }

    @Override
    public Set unionForSet(K setKey1, K setKey2) {
        return redisTemplate.opsForSet().union(setKey1, setKey2);
    }

    @Override
    public Set unionForSet(K setKey, Collection<K> anotherSet) {
        return redisTemplate.opsForSet().union(setKey, anotherSet);
    }

    @Override
    public Long unionAndStoreForSet(K setKey1, K setKey2, K newSetKey) {
        return redisTemplate.opsForSet().unionAndStore(setKey1, setKey2, newSetKey);
    }

    @Override
    public Long unionAndStoreForSet(K setKey1, Collection<K> anotherSet, K newSetKey) {
        return redisTemplate.opsForSet().unionAndStore(setKey1, anotherSet, newSetKey);
    }

    @Override
    public Long addForSet(K key, V... values) {
        return redisTemplate.opsForSet().add(key,values);
    }

    @Override
    public Boolean isMemberForSet(K setKey, V value) {
        return redisTemplate.opsForSet().isMember(setKey, value);
    }

    @Override
    public Set<V> membersForSet(K setKey) {
        return redisTemplate.opsForSet().members(setKey);
    }

    @Override
    public Long removeForSet(K setKey, V... values) {
        return redisTemplate.opsForSet().remove(setKey, values);
    }

    @Override
    public Long intersectAndStoreForZSet(K key, K otherKey, K destKey) {
        return redisTemplate.opsForZSet().intersectAndStore(key, otherKey, destKey);
    }

    @Override
    public Long intersectAndStoreForZSet(K key, Collection<K> otherKeys, K destKey) {
        return redisTemplate.opsForZSet().intersectAndStore(key, otherKeys, destKey);
    }

    @Override
    public Long unionAndStoreForZSet(K key, K otherKey, K destKey) {
        return redisTemplate.opsForZSet().unionAndStore(key, otherKey, destKey);
    }

    @Override
    public Long unionAndStoreForZSet(K key, Collection<K> otherKeys, K destKey) {
        return redisTemplate.opsForZSet().unionAndStore(key, otherKeys, destKey);
    }

    @Override
    public Set<V> rangeForZSet(K key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    @Override
    public Set<V> reverseRangeForZSet(K key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    @Override
    public Set<V> rangeByScoreForZSet(K key, double min, double max) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    @Override
    public Set<V> rangeByScoreForZSet(K key, double min, double max, long offset, long count) {
        return redisTemplate.opsForZSet().rangeByScore(key, min,max,offset, count);
    }

    @Override
    public Set<V> reverseRangeByScoreForZSet(K key, double min, double max) {
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
    }

    @Override
    public Set<V> reverseRangeByScoreForZSet(K key, double min, double max, long offset, long count) {
        return redisTemplate.opsForZSet().reverseRangeByScore(key,min,max,offset, count);
    }

    @Override
    public Boolean addForZSet(K key, V value, double score) {
        return redisTemplate.opsForZSet().add(key,value,score);
    }

    @Override
    public Double incrementScoreForZSet(K key, V value, double delta) {
        return redisTemplate.opsForZSet().incrementScore(key, value,delta);
    }

    @Override
    public Long rankForZSet(K key, Object o) {
        return redisTemplate.opsForZSet().rank(key, o);
    }

    @Override
    public Long reverseRankForZSet(K key, Object o) {
        return redisTemplate.opsForZSet().reverseRank(key, o);
    }

    @Override
    public Double scoreForZSet(K key, Object o) {
        return redisTemplate.opsForZSet().score(key ,o);
    }

    @Override
    public Long removeForZSet(K key, V... values) {
        return redisTemplate.opsForZSet().remove(key ,values);
    }

    @Override
    public Long removeRangeForZSet(K key, long start, long end) {
        return redisTemplate.opsForZSet().removeRange(key, start, end);
    }

    @Override
    public Long removeRangeByScoreForZSet(K key, double min, double max) {
        return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }

    @Override
    public Long countForZSet(K key, double min, double max) {
        return redisTemplate.opsForZSet().count(key, min, max);
    }

    @Override
    public Long sizeForZSet(K key) {
        return redisTemplate.opsForZSet().size(key);
    }

    @Override
    public Long zCardForZSet(K key) {
        return redisTemplate.opsForZSet().zCard(key);
    }

    @Override
    public Long deleteForHash(K key, Object... hashKeys) {
        return redisTemplate.opsForHash().delete((K)key, hashKeys);
    }

    @Override
    public Boolean hasKeyForHash(K key, K hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    @Override
    public V getForHash(K key, K hashKey) {
        return (V)redisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    public List<V> multiGetForHash(K key, Collection<K> hashKeys) {
        return (List<V>)redisTemplate.opsForHash().multiGet(key, (Collection<Object>)hashKeys);
    }

    @Override
    public Long incrementForHash(K key, K hashKey, long delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    @Override
    public Double incrementForHash(K key, K hashKey, double delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    @Override
    public Set keysForHash(K key) {
        return redisTemplate.opsForHash().keys(key);
    }

    @Override
    public Long sizeForHash(K key) {
        return redisTemplate.opsForHash().size(key);
    }

    @Override
    public void setForHash(K key, K hashKey, V value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @Override
    public Boolean setIfAbsentForHash(K key, K hashKey, V value) {
        return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
    }

    @Override
    public List<V> valuesForHash(K key) {
        return (List<V>)redisTemplate.opsForHash().values(key);
    }

    @Override
    public Map<K, V> entriesForHash(K key) {
        return (Map<K, V>)redisTemplate.opsForHash().entries(key);
    }

    @Override
    public Long differenceAndStoreForSet(K setKey, Collection<K> anotherSet, K newSetKey) {
        return redisTemplate.opsForSet().differenceAndStore(setKey, anotherSet, newSetKey);
    }

    @Override
    public void setAllForHash(K key, Map<? extends K, ? extends V> m) {
        redisTemplate.opsForHash().putAll(key, m);
    }
}
