package com.myzh.upm.user;

import java.util.List;

import com.myzh.upm.entity.UserCfg;
import com.myzh.framework.context.SpringContext;

public class UserCfgUtils {
	
	public static List<UserCfg> getUserCfg(String uid, String ...keys) {
		UserService srv = SpringContext.getBean("userService");
		return srv.queryUserCfg(keys);
	}
	
	public static UserCfg getUserCfg(Integer uid, String key) {
		UserService srv = SpringContext.getBean("userService");
		return srv.queryUserCfg(key);
	}
}
