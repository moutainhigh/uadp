package com.myzh.framework.data.jdbc.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myzh.framework.data.jdbc.JdbcDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/context/test_*.xml" })
public class JdbcTests extends AbstractJUnit4SpringContextTests{

	@Resource
	private JdbcDao dao;
	
	@Test
	public void testInsert() {
		String sql = "insert into upm_user(name, login_name, password) value('张三', 'zhangsan', 'zhangsan')";
		dao.insert(sql);
	}
	
	@Test
	public void insertWithParamTest() {
		String sql = "insert into upm_user(name, login_name, password) value(:name, :login_name, :password)";
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("name", "李四");
		p.put("login_name", "lisi");
		p.put("password", "lisi1");
		p.put("sex", "nan");
		dao.insert(sql, p);
	}
	
	@Test
	public void updateTest() {
		String sql = "update upm_user set name='张三1' where login_name='zhangsan'";
		dao.update(sql);
	}
	
	@Test
	public void updateWithParamTest() {
		String sql = "update upm_user set name=:name where login_name=:loginName";
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("name", "李四2");
		p.put("loginName", "lisi");
		p.put("password", "lisi1");
		p.put("sex", "nan");
		dao.update(sql, p);
	}
	
	/*//@Test
	public void deleteTest() {
		String sql = "delete from upm_user where login_name='zhangsan'";
		dao.delete(sql);
	}
	
	//@Test
	public void deleteWithParamTest() {
		String sql = "delete from upm_user where login_name=?";
		dao.delete(sql, "lisi");
	}*/
	
	@Test
	public void queryForListTest() {
		String sql = "select * from upm_user";
		List<Map<String, Object>> lst = dao.queryForList(sql);
		//System.out.println(lst);
	}
	
	@Test
	public void queryForListTypeTest() {
		Date d = new Date();
		String sql = "select * from upm_user";
		List<User> lst = dao.queryForList(sql, User.class);
		System.out.println(lst);
		Date s = new Date();
		System.out.println(s.getTime() - d.getTime());
	}
	
	@Test
	public void queryForListWithParamTest() {
		Date d = new Date();
		String sql = "select * from upm_user where login_name=:loginName";
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("loginName", "lisi");
		List<Map<String, Object>> lst = dao.queryForList(sql, m);
		System.out.println(lst);
		Date s = new Date();
		System.out.println(s.getTime() - d.getTime());
	}
	
	
}
