package com.upbos.upm.login;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.upbos.framework.data.mybatis.MybatisTplDao;
import com.upbos.upm.entity.Module;
import com.upbos.upm.entity.Org;
import com.upbos.upm.entity.Role;
import com.upbos.upm.entity.User;
import com.upbos.util.encrypt.MD5;

@Service
public class LoginService {
	@Resource(name="mybatisTplDao")
	private MybatisTplDao dao;
	
	@Value("${has.PasswordSalt}")
	public boolean hasPasswordSalt = true;
	
	
	public int needModifyPassword(String uid) {
		Date date = dao.queryForOne("upm.login.queryPasswordModifyTime", uid);
		if(date == null) {
			return 1;
		}else {
			Date s = new Date();
			if(daysBetween(date, s) >= 90) {
				return 2;
			}
		}
		
		return 0;
	}
	
	public User queryUserByLoginName(User user) {
		user.setPassword(MD5.toMD5(user.getPassword() + (hasPasswordSalt ? "{PONY}" : "")));
		return dao.queryForOne("upm.login.queryUserByLoginName", user);
	}
	
	public Map<String, Object> modifyPassword(String uid, String oldPassword, String newPassword) {
		User user = dao.queryForOne("upm.user.queryUserByUid", uid);
		Map<String, Object> rtn = new HashMap<String, Object>();
		
		if(!user.getPassword().equals(MD5.toMD5(oldPassword + (hasPasswordSalt ? "{PONY}" : "")))) {
			rtn.put("success", false);
			rtn.put("message", "旧密码不正确。");
			return rtn;
		}
		
		user.setPassword(MD5.toMD5(newPassword + (hasPasswordSalt ? "{PONY}" : "")));
		dao.update("upm.user.resetPassword", user);
		rtn.put("success", true);
		rtn.put("message", "修改密码成功。");
		return rtn;
	}
	
	public Map<String, Object> main(String uid) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("menu", queryModuleTree(uid));
		return result;
	}
	
	private List<Module> queryModuleTree(String uid) {
		List<Module> userMenu = findUserMenu(uid);

		Module root = new Module();
		root.setId(0);
		renderTree(root, userMenu);
		sortTree(root.getChildren());
		return root.getChildren();
	}
	
	
	private List<Module> findUserMenu(String uid) {
		List<Module> menu = dao.queryForList("upm.login.queryParentModule");
		List<Module> modules = dao.queryForList("upm.login.queryUserModule", uid);
		List<Module> userMenu = new ArrayList<Module>();
		
		for(Module module : modules) {
			boolean hasParentMenu = false;
			for(Module m : menu)  {
				if(module.getParentId().intValue() == m.getId().intValue()) {
					if(m.getChildren() == null) {
						m.setChildren(new ArrayList<Module>());
					}
					m.getChildren().add(module);
					hasParentMenu = true;
					findParentMenu(m, menu, userMenu);
				}
			}
			if(!hasParentMenu) {
				userMenu.add(module);
			}
		}
		
		return userMenu;
	}
	
	private void findParentMenu(Module node, List<Module> menu, List<Module> userMenu) {
		if(node.getParentId().intValue() != 0) {
			for(Module m : menu) {
				if(node.getParentId().intValue() == m.getId().intValue()) {
					findParentMenu(m, menu, userMenu);
					break;
				}
			}
		}
		
		if(!isExist(userMenu, node)) {
			userMenu.add(node); 
		}
	}
	
	private boolean isExist(List<Module> userMenu, Module m) {
		for(Module mod : userMenu) {
			if(mod.getId().intValue() == m.getId().intValue()) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	/**
	 * @Method: renderTree
	 * @Description: 
	 * @param @param parentModule
	 * @param @param tree
	 * @return void
	 * @throws
	 */
	private void renderTree(Module parentModule, List<Module> tree) {
		Integer parentKey =(Integer)parentModule.getId();
		for(int i = 0; i < tree.size(); i++) {
			Module m = tree.get(i);
			Integer parentId = m.getParentId();
			if(parentId == null) {
				parentId = 0;
			}
			
			if(parentId == parentKey.intValue()) {
				List<Module> children = parentModule.getChildren();
				if(children == null) {
					children = new ArrayList<Module>();
					parentModule.setChildren(children);
				}
				children.add(m);
				renderTree(m, tree);
			}
		}
	}
	
	private void sortTree(List<Module> menu) {
		Collections.sort(menu);
		for(Module m : menu) {
			if(m.getChildren() != null && m.getChildren().size() > 0) {
				sortTree(m.getChildren());
			}
		}
	}
	
	public Org queryOrgById(Integer orgId) {
		return dao.queryForOne("upm.login.queryOrgById", orgId);
	}
	
	public List<Role> queryUserRoleList(String uid) {
		return dao.queryForList("upm.login.queryUserRoleList", uid);
	}
	
	public int daysBetween(Date date1,Date date2)  
    {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date1);  
        long time1 = cal.getTimeInMillis();               
        cal.setTime(date2);  
        long time2 = cal.getTimeInMillis();       
        long between_days=(time2-time1)/(1000*3600*24);  
          
       return Integer.parseInt(String.valueOf(between_days));         
    }  
}
