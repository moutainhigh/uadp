package com.upbos.cache.memcache;

import com.upbos.cache.CacheService;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by chensg on 2016/5/31.
 */
public class MemcacheServiceImpl implements CacheService {

    @Override
    public Boolean hasKey(Object key) {
        return null;
    }

    @Override
    public void delete(Object key) {

    }

    @Override
    public void delete(Collection keys) {

    }

    @Override
    public Boolean expire(Object key, long expireTime, TimeUnit unit) {
        return null;
    }

    @Override
    public Boolean expireAt(Object key, Date expireTime) {
        return null;
    }

    @Override
    public void set(Object key, Object value) {

    }

    @Override
    public void set(Object key, Object value, long expireTime, TimeUnit unit) {

    }

    @Override
    public void multiSet(Map map) {

    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public Set keys(Object key) {
        return null;
    }

    @Override
    public Object getAndSet(Object key, Object value) {
        return null;
    }

    @Override
    public List multiGet(Collection keys) {
        return null;
    }

    @Override
    public Long increment(Object key, long incrementValue) {
        return null;
    }

    @Override
    public Double increment(Object key, double incrementValue) {
        return null;
    }

    @Override
    public List rangeForList(Object key, long start, long end) {
        return null;
    }

    @Override
    public void trimForList(Object key, long start, long end) {

    }

    @Override
    public Long sizeForList(Object key) {
        return null;
    }

    @Override
    public Long leftPushForList(Object key, Object value) {
        return null;
    }

    @Override
    public Long listPushAllForList(Object key, Object[] values) {
        return null;
    }

    @Override
    public Long leftPushAllForList(Object key, Collection values) {
        return null;
    }

    @Override
    public Long leftPushIfPresentForList(Object key, Object value) {
        return null;
    }

    @Override
    public Long leftPushForList(Object key, Object pivot, Object value) {
        return null;
    }

    @Override
    public Long rightPushForList(Object key, Object value) {
        return null;
    }

    @Override
    public Long rightPushAllForList(Object key, Object[] values) {
        return null;
    }

    @Override
    public Long rightPushAllForList(Object key, Collection values) {
        return null;
    }

    @Override
    public Long rightPushIfPresentForList(Object key, Object value) {
        return null;
    }

    @Override
    public Long rightPushForList(Object key, Object pivot, Object value) {
        return null;
    }

    @Override
    public void setForList(Object key, long index, Object value) {

    }

    @Override
    public Long removeForList(Object key, long i, Object value) {
        return null;
    }

    @Override
    public Object indexForList(Object key, long index) {
        return null;
    }

    @Override
    public Object leftPopForList(Object key) {
        return null;
    }

    @Override
    public Object leftPopForList(Object key, long timeout, TimeUnit unit) {
        return null;
    }

    @Override
    public Object rightPopForList(Object key) {
        return null;
    }

    @Override
    public Object rightPopForList(Object key, long timeout, TimeUnit unit) {
        return null;
    }

    @Override
    public Object rightPopAndLeftPushForList(Object sourceKey, Object destinationKey) {
        return null;
    }

    @Override
    public Object rightPopAndLeftPushForList(Object sourceKey, Object destinationKey, long timeout, TimeUnit unit) {
        return null;
    }

    @Override
    public Set differenceForSet(Object setKey1, Object setKey2) {
        return null;
    }

    @Override
    public Set differenceForSet(Object setKey, Collection anotherSet) {
        return null;
    }

    @Override
    public Long differenceAndStoreForSet(Object setKey1, Object setKey2, Object newSetKey) {
        return null;
    }

    @Override
    public Long differenceAndStoreForSet(Object setKey, Collection anotherSet, Object newSetKey) {
        return null;
    }

    @Override
    public Set intersectForSet(Object setKye1, Object setKey2) {
        return null;
    }

    @Override
    public Set intersectForSet(Object setKey, Collection anotherSet) {
        return null;
    }

    @Override
    public Long intersectAndStoreForSet(Object setKey1, Object setKey2, Object newSetKey) {
        return null;
    }

    @Override
    public Long intersectAndStoreForSet(Object setKey, Collection anotherSet, Object newSetKey) {
        return null;
    }

    @Override
    public Set unionForSet(Object setKey1, Object setKey2) {
        return null;
    }

    @Override
    public Set unionForSet(Object setKey, Collection anotherSet) {
        return null;
    }

    @Override
    public Long unionAndStoreForSet(Object setKey1, Object setKey2, Object newSetKey) {
        return null;
    }

    @Override
    public Long unionAndStoreForSet(Object setKey1, Collection anotherSet, Object newSetKey) {
        return null;
    }

    @Override
    public Long addForSet(Object key, Object[] values) {
        return null;
    }

    @Override
    public Boolean isMemberForSet(Object setKey, Object value) {
        return null;
    }

    @Override
    public Set membersForSet(Object setKey) {
        return null;
    }

    @Override
    public Long removeForSet(Object setKey, Object... values) {
        return null;
    }

    @Override
    public Long intersectAndStoreForZSet(Object key, Object otherKey, Object destKey) {
        return null;
    }

    @Override
    public Long intersectAndStoreForZSet(Object key, Collection otherKeys, Object destKey) {
        return null;
    }

    @Override
    public Long unionAndStoreForZSet(Object key, Object otherKey, Object destKey) {
        return null;
    }

    @Override
    public Long unionAndStoreForZSet(Object key, Collection otherKeys, Object destKey) {
        return null;
    }

    @Override
    public Set rangeForZSet(Object key, long start, long end) {
        return null;
    }

    @Override
    public Set reverseRangeForZSet(Object key, long start, long end) {
        return null;
    }

    @Override
    public Set rangeByScoreForZSet(Object key, double min, double max) {
        return null;
    }

    @Override
    public Set rangeByScoreForZSet(Object key, double min, double max, long offset, long count) {
        return null;
    }

    @Override
    public Set reverseRangeByScoreForZSet(Object key, double min, double max) {
        return null;
    }

    @Override
    public Set reverseRangeByScoreForZSet(Object key, double min, double max, long offset, long count) {
        return null;
    }

    @Override
    public Boolean addForZSet(Object key, Object value, double score) {
        return null;
    }

    @Override
    public Double incrementScoreForZSet(Object key, Object value, double delta) {
        return null;
    }

    @Override
    public Long rankForZSet(Object key, Object o) {
        return null;
    }

    @Override
    public Long reverseRankForZSet(Object key, Object o) {
        return null;
    }

    @Override
    public Double scoreForZSet(Object key, Object o) {
        return null;
    }

    @Override
    public Long removeForZSet(Object key, Object... values) {
        return null;
    }

    @Override
    public Long removeRangeForZSet(Object key, long start, long end) {
        return null;
    }

    @Override
    public Long removeRangeByScoreForZSet(Object key, double min, double max) {
        return null;
    }

    @Override
    public Long countForZSet(Object key, double min, double max) {
        return null;
    }

    @Override
    public Long sizeForZSet(Object key) {
        return null;
    }

    @Override
    public Long zCardForZSet(Object key) {
        return null;
    }

    @Override
    public Long deleteForHash(Object key, Object... hashKeys) {
        return null;
    }

    @Override
    public Boolean hasKeyForHash(Object key, Object hashKey) {
        return null;
    }

    @Override
    public Object getForHash(Object key, Object hashKey) {
        return null;
    }

    @Override
    public List multiGetForHash(Object key, Collection hashKeys) {
        return null;
    }

    @Override
    public Long incrementForHash(Object key, Object hashKey, long delta) {
        return null;
    }

    @Override
    public Double incrementForHash(Object key, Object hashKey, double delta) {
        return null;
    }

    @Override
    public Set keysForHash(Object key) {
        return null;
    }

    @Override
    public Long sizeForHash(Object key) {
        return null;
    }

    @Override
    public void setAllForHash(Object key, Map m) {

    }

    @Override
    public void setForHash(Object key, Object hashKey, Object value) {

    }

    @Override
    public Boolean setIfAbsentForHash(Object key, Object hashKey, Object value) {
        return null;
    }

    @Override
    public List valuesForHash(Object key) {
        return null;
    }

    @Override
    public Map entriesForHash(Object key) {
        return null;
    }

}
