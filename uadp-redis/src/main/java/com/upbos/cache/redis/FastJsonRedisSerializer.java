package com.upbos.cache.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.SerializationUtils;
import org.springframework.lang.Nullable;

/**
 * <b>Application name：</b> FastJsonRedisSerializer.java <br>
 * <b>Application describing:</b> <br>
 * <b>Copyright：</b> Copyright &copy; 2017 明医众禾科技（北京）有限责任公司 版权所有。<br>
 * <b>Company：</b> 明医众禾科技（北京）有限责任公司 <br>
 * <b>Date：</b> 2017年10月22日 01:42 <br>
 * <b>author：</b> <a href="mailto:wangjz@miyzh.com"> wangjz </a>
 * <b>version：</b>V2.0.0
 */
public class FastJsonRedisSerializer implements RedisSerializer<Object> {
    private static final SerializerFeature[] features = {
            SerializerFeature.DisableCircularReferenceDetect,//打开循环引用检测，JSONField(serialize = false)不循环
            SerializerFeature.WriteDateUseDateFormat,//默认使用系统默认 格式日期格式化
//SerializerFeature.WriteMapNullValue, //输出空置字段
            //SerializerFeature.WriteNullListAsEmpty,//list字段如果为null，输出为[]，而不是null
            //SerializerFeature.WriteNullNumberAsZero,// 数值字段如果为null，输出为0，而不是null
            //SerializerFeature.WriteNullBooleanAsFalse,//Boolean字段如果为null，输出为false，而不是null
            //SerializerFeature.WriteNullStringAsEmpty//字符类型字段如果为null，输出为""，而不是null
    };
    @Nullable
    @Override
    public byte[] serialize(@Nullable Object o) throws SerializationException {
        byte[] s = JSON.toJSONBytes(o, features);
        return s;
    }

    @Nullable
    @Override
    public Object deserialize(@Nullable byte[] bytes) throws SerializationException {
        if(bytes == null) {
            return new byte[0];
        }
        return JSON.parseObject(bytes, Object.class);
    }
}
