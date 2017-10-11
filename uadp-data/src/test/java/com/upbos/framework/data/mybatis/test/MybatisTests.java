package com.upbos.framework.data.mybatis.test;

import com.upbos.framework.data.jdbc.JdbcDao;
import com.upbos.framework.data.mybatis.MybatisTplDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/context/test_*.xml" })
public class MybatisTests extends AbstractJUnit4SpringContextTests{

	private static Logger logger = LoggerFactory.getLogger(MybatisTests.class);

	@Resource
	private MybatisTplDao dao;
	
	@Test
	public void testPagination() {
		List<User> users = dao.queryForPaginationWithoutTotal(1, 5, "upm.user.queryUserList");

		logger.error("错误信息打印....");
		System.out.println(users.size());
	}
	

	
}
