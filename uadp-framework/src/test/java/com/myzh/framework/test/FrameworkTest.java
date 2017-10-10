package com.myzh.framework.test;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/context/test_*.xml" })
public class FrameworkTest extends AbstractJUnit4SpringContextTests{

	@Resource
	private DemoService srv;
	
	@Value("${context.component.scan}")
	private String scanDir;
	
	@Test
	public void hello() {
		String result = srv.queryServiceName("hello");
		System.out.println(result);
	}
	
	@Test
	public void getValue() {
		System.out.println(scanDir);
		Assert.assertEquals("com.myzh", scanDir);
	}
}
