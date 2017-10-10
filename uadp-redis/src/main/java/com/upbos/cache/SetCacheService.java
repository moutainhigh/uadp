package com.upbos.cache;

import java.util.Collection;
import java.util.Set;

/**
 * Created by chensg on 2016/6/2.
 */
public interface SetCacheService<K, V> {
    /**
     * 获取取两个集合的差集
     * @param setKey1 集合1的key
     * @param setKey2 集合2的key
     * @return 差集
     */
    Set<V> differenceForSet(K setKey1, K setKey2);

    /**
     * 获取取两个集合的差集
     * @param setKey 集合1的key
     * @param anotherSet 集合2
     * @return 差集
     */
    Set<V> differenceForSet(K setKey, Collection<K> anotherSet);

    /**
     * 获取取两个集合的差集并将差集缓存
     * @param setKey1 集合1的key
     * @param setKey2 集合2的key
     * @param newSetKey 差集的key
     * @return
     */
    Long differenceAndStoreForSet(K setKey1, K setKey2, K newSetKey);

    /**
     * 获取取两个集合的差集并将差集缓存
     * @param setKey 集合1的key
     * @param anotherSet 集合2
     * @param newSetKey 差集的key
     * @return
     */
    Long differenceAndStoreForSet(K setKey, Collection<K> anotherSet, K newSetKey);

    /**
     * 获取两个集合的交集
     * @param setKye1 集合1的key
     * @param setKey2 集合2的key
     * @return 交集
     */
    Set<V> intersectForSet(K setKye1, K setKey2);
    /**
     * 获取两个集合的交集
     * @param setKey 集合1的key
     * @param anotherSet 集合2
     * @return 交集
     */
    Set<V> intersectForSet(K setKey, Collection<K> anotherSet);

    /**
     * 获取两个集合的交集,并将交集缓存为新的集合
     * @param setKey1 集合1的key
     * @param setKey2 集合2的key
     * @param newSetKey 交集集合key
     * @return
     */
    Long intersectAndStoreForSet(K setKey1, K setKey2, K newSetKey);

    /**
     * 获取两个集合的交集,并将交集缓存为新的集合
     * @param setKey 集合1的key
     * @param anotherSet 集合2
     * @param newSetKey 交集集合key
     * @return
     */
    Long intersectAndStoreForSet(K setKey, Collection<K> anotherSet, K newSetKey);

    /**
     * 合并两个set集合
     * @param setKey1 集合1
     * @param setKey2 集合2
     * @return 合并后的集合
     */
    Set<V> unionForSet(K setKey1, K setKey2);
    /**
     * 合并两个set集合
     * @param setKey 集合1的key
     * @param anotherSet 集合2
     * @return 合并后的集合
     */
    Set<V> unionForSet(K setKey, Collection<K> anotherSet);

    /**
     * 合并两个set集合并缓存为新集合
     * @param setKey1 集合1的key
     * @param setKey2 集合2的key
     * @param newSetKey 新集合的key
     * @return
     */
    Long unionAndStoreForSet(K setKey1, K setKey2, K newSetKey);

    /**
     * 合并两个set集合并缓存为新集合
     * @param setKey1 集合1的key
     * @param anotherSet 集合2
     * @param newSetKey 新集合的key
     * @return
     */
    Long unionAndStoreForSet(K setKey1, Collection<K> anotherSet, K newSetKey);

    /**
     * 向集合添加数据
     * @param key 集合key
     * @param values 数据项
     * @return
     */
    Long addForSet(K key, V... values);

    /**
     * 判断是否是集合成员
     * @param setKey 集合key
     * @param value 数据值
     * @return
     */
    Boolean isMemberForSet(K setKey, V value);

    /**
     * 返回集合成员数据
     * @param setKey
     * @return
     */
    Set<V> membersForSet(K setKey);

    /**
     * 删除指定集合里的数据项
     * @param setKey 集合key
     * @param values 删除的数据
     * @return
     */
    Long removeForSet(K setKey, V... values);
}
