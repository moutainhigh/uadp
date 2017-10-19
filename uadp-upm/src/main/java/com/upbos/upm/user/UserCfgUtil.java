package com.upbos.upm.user;

import java.util.List;

import com.upbos.upm.entity.UserCfg;
import com.upbos.framework.context.SpringContext;

public class UserCfgUtil {
	
	public static List<UserCfg> listUserCfg(String uid, String ...keys) {
		UserService srv = SpringContext.getBean("userService");
		return srv.listUserCfg(uid, keys);
	}
	
	public static UserCfg getUserCfg(String uid, String key) {
		UserService srv = SpringContext.getBean("userService");
		return srv.getUserCfg(uid, key);
	}
}
