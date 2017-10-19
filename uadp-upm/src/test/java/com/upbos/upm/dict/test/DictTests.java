package com.upbos.upm.dict.test;

import com.upbos.upm.entity.DictMapping;
import com.upbos.upm.dict.DictUtil;
import com.upbos.upm.entity.User;
import com.upbos.upm.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/context/test_*.xml" })
public class DictTests extends AbstractJUnit4SpringContextTests{

	@Resource
	private UserService srv;

	@Test
	public void testTranscate() {
		List<User> userList = srv.queryUserListByOrgId(1);
		System.out.println("翻译前：" + userList);
		try {
			DictUtil.tranlate(userList, new DictMapping("user_type", "type", "typeName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("翻译后：" + userList);
	}

}
