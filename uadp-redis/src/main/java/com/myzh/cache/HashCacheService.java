/*******************************************************************************
 * @(#)MapCacheService.java 2016年06月27日 17:29
 * Copyright 2016 明医众禾科技（北京）有限责任公司. All rights reserved.
 *******************************************************************************/
package com.myzh.cache;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ScanOptions;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <b>Application name：</b> MapCacheService.java <br>
 * <b>Application describing： </b> <br>
 * <b>Copyright：</b> Copyright &copy; 2016 明医众禾科技（北京）有限责任公司 版权所有。<br>
 * <b>Company：</b> 明医众禾科技（北京）有限责任公司 <br>
 * <b>Date：</b> 2016年06月27日 17:29 <br>
 * <b>author：</b> <a href="mailto:chensg@miyzh.com"> chensg </a>
 * <b>version：</b>V1.0
 */
public interface HashCacheService<H, HK, HV> {
    /**
     * Delete given hash {@code hashKeys}.
     *
     * @param key must not be {@literal null}.
     * @param hashKeys must not be {@literal null}.
     * @return
     */
    Long deleteForHash(H key, Object... hashKeys);

    /**
     * Determine if given hash {@code hashKey} exists.
     *
     * @param key must not be {@literal null}.
     * @param hashKey must not be {@literal null}.
     * @return
     */
    Boolean hasKeyForHash(H key, HK hashKey);

    /**
     * Get value for given {@code hashKey} from hash at {@code key}.
     *
     * @param key must not be {@literal null}.
     * @param hashKey must not be {@literal null}.
     * @return
     */
    HV getForHash(H key, HK hashKey);

    /**
     * Get values for given {@code hashKeys} from hash at {@code key}.
     *
     * @param key must not be {@literal null}.
     * @param hashKeys must not be {@literal null}.
     * @return
     */
    List<HV> multiGetForHash(H key, Collection<HK> hashKeys);

    /**
     * Increment {@code value} of a hash {@code hashKey} by the given {@code delta}.
     *
     * @param key must not be {@literal null}.
     * @param hashKey must not be {@literal null}.
     * @param delta
     * @return
     */
    Long incrementForHash(H key, HK hashKey, long delta);

    /**
     * Increment {@code value} of a hash {@code hashKey} by the given {@code delta}.
     *
     * @param key must not be {@literal null}.
     * @param hashKey must not be {@literal null}.
     * @param delta
     * @return
     */
    Double incrementForHash(H key, HK hashKey, double delta);

    /**
     * Get key set (fields) of hash at {@code key}.
     *
     * @param key must not be {@literal null}.
     * @return
     */
    Set<HK> keysForHash(H key);

    /**
     * Get size of hash at {@code key}.
     *
     * @param key must not be {@literal null}.
     * @return
     */
    Long sizeForHash(H key);

    /**
     * Set multiple hash fields to multiple values using data provided in {@code m}.
     *
     * @param key must not be {@literal null}.
     * @param m must not be {@literal null}.
     */
    void setAllForHash(H key, Map<? extends HK, ? extends HV> m);

    /**
     * Set the {@code value} of a hash {@code hashKey}.
     *
     * @param key must not be {@literal null}.
     * @param hashKey must not be {@literal null}.
     * @param value
     */
    void setForHash(H key, HK hashKey, HV value);

    /**
     * Set the {@code value} of a hash {@code hashKey} only if {@code hashKey} does not exist.
     *
     * @param key must not be {@literal null}.
     * @param hashKey must not be {@literal null}.
     * @param value
     * @return
     */
    Boolean setIfAbsentForHash(H key, HK hashKey, HV value);

    /**
     * Get entry set (values) of hash at {@code key}.
     *
     * @param key must not be {@literal null}.
     * @return
     */
    List<HV> valuesForHash(H key);

    /**
     * Get entire hash stored at {@code key}.
     *
     * @param key must not be {@literal null}.
     * @return
     */
    Map<HK, HV> entriesForHash(H key);
}