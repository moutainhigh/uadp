package com.upbos.cache.redis.test;

import com.alibaba.fastjson.JSON;
import com.upbos.cache.CacheService;
import com.upbos.cache.redis.FastJsonRedisSerializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/context/context-*.xml" })
public class RedisTest extends AbstractJUnit4SpringContextTests{

	@Resource(name="redisService")
	private CacheService cacheService;

	@Test
	public void testSet() {
		long s = System.currentTimeMillis();
		for (int i = 0 ; i < 2; i++) {
			cacheService.set("key1", "value1");
//		Map<String, String> p = new HashMap<>();
//		p.put("p1", "v1");
//		p.put("p2", "v2");
//		p.put("p3", "v3");
//		p.put("p4", "v4");
//		p.put("p5", "v5");
			Student student = new Student();
			student.setId("100110");
			student.setAddr("多大点事多发达手动阀手动阀手动阀大法师大厦发送东方四大发生的发生的阿斯蒂芬");
			student.setAge("101");
			student.setIdNo("199292992929292929222");
			student.setName("真时尚");
//		cacheService.multiSet(p);
			cacheService.set("student" +i, student);
			//System.out.println(cacheService.getAndSet("p1", "v11"));
		}
		long e = System.currentTimeMillis();
		System.out.println(e-s);
		//5s 643ms
		// 27380 27s 383ms
	}
}
