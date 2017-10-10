package com.myzh.cache;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.query.SortQuery;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 缓存服务
 * 集成不同数据类型的缓存服务接口
 * Created by chensg on 2016/5/26.
 */
public interface CacheService<K,V> extends
        KeyValueCacheService<K, V>,
        HashCacheService<K, K, V>,
        ListCacheService<K, V>,
        SetCacheService<K, V>,
        ZSetCacheService<K, V> {
}
