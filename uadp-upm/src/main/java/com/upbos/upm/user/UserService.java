package com.upbos.upm.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.upbos.upm.cfg.CfgUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upbos.framework.data.mybatis.MybatisTplDao;
import com.upbos.upm.entity.Cfg;
import com.upbos.upm.entity.User;
import com.upbos.upm.entity.UserCfg;
import com.upbos.util.encrypt.MD5;

/**
 * <p>ClassName: UserService</p>
 * <p>Description: 用户管理服务层</p>
 * <p>Company： upbos.com</p>
 * @author wangjz
 * @date 2016年11月27日 下午5:18:15
 * @version v2.5.0
 */
@Service("userService")
public class UserService {
	
	@Resource(name="mybatisTplDao")
	private MybatisTplDao dao;
	
	@Value("${has.PasswordSalt}")
	public boolean hasPasswordSalt = true;
	
	public List<User> queryUserListByOrgId(Integer orgId) {
		List<User> userList = dao.queryForList("upm.user.queryUserListByOrgId", orgId);
		return userList;
	}
	
	@Transactional
	public void insertUser(User user) {
		//密码加密处理
		user.setPassword(MD5.toMD5(user.getPassword() + (hasPasswordSalt ? "{PONY}" : "")));
		dao.insert("upm.user.insertUser", user);
	}
	
	@Transactional
	public void updateUser(User user) {
		dao.update("upm.user.updateUser", user);
	}
	
	@Transactional
	public void deleteUser(String uid) {
		dao.delete("upm.user.deleteUser", uid);
		dao.delete("upm.user.deleteUserRole", Integer.valueOf(uid));
	}
	
	@Transactional
	public void resetPassword(User user) {
		//密码加密处理
		user.setPassword(MD5.toMD5(user.getPassword() + (hasPasswordSalt ? "{PONY}" : "")));
		dao.update("upm.user.resetPassword", user);
	}
	
	/**
	 * @Method: checkLoginName
	 * @Description: 检查登录帐号是否唯一
	 * @param loginName
	 * @return
	 * @throws
	 */
	public boolean checkLoginNameOnly(String loginName) {
		int cnt = dao.queryForOne("upm.user.checkLoginName", loginName);
		if(cnt == 1) {
			return false;
		}else {
			return true;
		}
	}
	
	public List<UserCfg> listUserCfg(String uid, String ...keys) {
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("uid", uid);
		p.put("keys", keys);
		List<UserCfg> userCfgs = dao.queryForList("upm.user.listUserCfg", p);
		List<Cfg> cfgs = CfgUtil.listCfg(keys);
		if(cfgs == null) return null;
		for(Cfg cfg : cfgs) {
			boolean has = false;
			for(UserCfg userCfg : userCfgs) {
				if(cfg.getKey().equals(userCfg.getKey())) {
					has = true;
					break;
				}
			}
			
			if(!has) {
				UserCfg uc = new UserCfg();
				uc.setKey(cfg.getKey());
				uc.setValue(cfg.getValue());
				userCfgs.add(uc);
			}
		}
		return userCfgs;
	}
	
	public UserCfg getUserCfg(String uid, String key) {
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("uid", uid);
		p.put("key", key);
		UserCfg userCfg = dao.queryForOne("upm.user.getUserCfg", p);
		Cfg cfg = CfgUtil.getCfg(key);
		if(userCfg == null && cfg != null) {
			userCfg = new UserCfg();
			userCfg.setKey(cfg.getKey());
			userCfg.setValue(cfg.getValue());
		}
		return userCfg;
	}
	
	public Map<String, Object> ownerRole(String orgId, String uid) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> list = dao.queryForList("upm.user.queryAllRoleByOrgId", orgId);
		List<String> ownerRoleId = dao.queryForList("upm.user.queryUserRoleId", uid);
		result.put("allRole", list);
		result.put("ownerRole", ownerRoleId);
		return result;
	}
	
	@Transactional
	public void updateUserRole(String uid, String[] roles) {
		dao.delete("upm.user.deleteUserRole", Integer.valueOf(uid));
		for(String roleId : roles) {
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("uid", Integer.valueOf(uid));
			p.put("roleId", Integer.valueOf(roleId));
			dao.insert("upm.user.insertUserRole", p);
		}
	}
}
