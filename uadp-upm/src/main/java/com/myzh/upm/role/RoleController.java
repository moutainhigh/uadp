package com.myzh.upm.role;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myzh.session.SessionManager;
import com.myzh.session.SessionUser;
import com.myzh.upm.entity.Org;
import com.myzh.upm.entity.Role;

@RestController
@RequestMapping("upm/role")
public class RoleController {
	
	@Resource
	private RoleService srv;
	
	@Resource
	private SessionManager session;
	
	@RequestMapping("queryRoleList")
	public List<Role> queryRoleList(HttpServletRequest req, Integer orgId) {
		return srv.queryRoleList(orgId);
	}
	
	@RequestMapping("insertRole")
	public void insertRole(Role role) {
		srv.insertRole(role);
	}
	
	@RequestMapping("updateRole")
	public void updateRole(Role role) {
		srv.updateRole(role);
	}
	
	@RequestMapping("deleteRole")
	public void deleteRole(Integer roleId) {
		srv.deleteRole(roleId);
	}
	
	@RequestMapping("updateRoleModule")
	public void updateRoleModule(Integer roleId, 
			@RequestParam(value="modules[]") String[] modules) {
		srv.updateRoleModule(roleId, modules);
	}
	
	@RequestMapping("queryRoleModuleList")
	public String[] queryRoleModuleList(Integer roleId) {
		return srv.queryRoleModuleList(roleId);
	}
}