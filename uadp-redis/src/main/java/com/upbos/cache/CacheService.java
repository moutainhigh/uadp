package com.upbos.cache;

/**
 * 缓存服务
 * 集成不同数据类型的缓存服务接口
 * @author wangjz
 * Created by chensg on 2016/5/26.
 */
public interface CacheService<K,V> extends
        KeyValueCacheService<K, V>,
        HashCacheService<K, K, V>,
        ListCacheService<K, V>,
        SetCacheService<K, V>,
        ZSetCacheService<K, V> {
}
