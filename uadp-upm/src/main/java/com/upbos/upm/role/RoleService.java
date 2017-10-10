package com.upbos.upm.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upbos.framework.data.mybatis.MybatisTplDao;
import com.upbos.upm.entity.Role;

@Service
public class RoleService {
	
	@Resource(name="mybatisTplDao")
	private MybatisTplDao dao;
	
	
	public List<Role> queryRoleList(Integer orgId) {
		List<Role> list = dao.queryForList("upm.role.queryRoleList", orgId);
		if(list != null) {
			for(Role role : list) {
				if(role.getToOrgId().intValue() == orgId.intValue()) {
					role.setIsLocalRole(true);
				}else {
					role.setIsLocalRole(false);
				}
			}
		}
		return list;
	}
	
	@Transactional
	public void insertRole(Role role) {
		dao.insert("upm.role.insertRole", role);
	}
	
	@Transactional
	public void updateRole(Role role) {
		dao.update("upm.role.updateRole", role);
	}
	
	@Transactional
	public void deleteRole(Integer roleId) {
		dao.delete("upm.role.deleteRole", roleId);
		dao.delete("upm.role.deleteRoleModule", roleId);
		dao.delete("upm.role.deleteUserRole", roleId);
	}
	
	@Transactional
	public void updateRoleModule(Integer roleId, String[] modules) {
		if(modules == null) {
			return;
		}
		
		dao.delete("upm.role.deleteRoleModule", roleId);
		
		for(String moduleId : modules) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roleId", roleId);
			map.put("moduleId", moduleId);
			dao.insert("upm.role.insertRoleModule", map);
		}
	}
	
	public String[] queryRoleModuleList(Integer roleId) {
		List<Map<String, Integer>>  list = dao.queryForList("upm.role.queryRoleModuleList", roleId);
		if(list == null || list.isEmpty()) return new String[0];
		String[] arr = new String[list.size()];
		for(int i = 0; i < list.size(); i++) {
			arr[i] = String.valueOf(list.get(i).get("moduleId"));
		}
		return arr;
	}
}
