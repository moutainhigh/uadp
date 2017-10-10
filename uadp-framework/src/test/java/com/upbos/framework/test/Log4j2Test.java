package com.upbos.framework.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/context/test_*.xml" })
public class Log4j2Test extends AbstractJUnit4SpringContextTests{

	private static Logger log = LogManager.getLogger(Log4j2Test.class);
	@Test
	public void debug() {
		log.debug("我来测试log4j2 debug");
	}
	
	@Test
	public void Info() {
		log.debug("我来测试log4j2 info");
	}
	
	@Test
	public void warn() {
		log.debug("我来测试log4j2 warn");
	}
	
	@Test
	public void error() {
		log.error("我来测试log4j2 error");
		Exception e = new Exception("dddd");
		log.error("错误如下:", e);
	}
}
