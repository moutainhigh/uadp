package com.upbos.upm.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upbos.session.session.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upbos.session.SessionManager;
import com.upbos.session.SessionUser;
import com.upbos.upm.entity.Org;
import com.upbos.upm.entity.Role;
import com.upbos.upm.entity.User;
import com.upbos.upm.entity.UserCfg;
import com.upbos.upm.randomCode.RandomCodeInterface;
import com.upbos.upm.user.UserCfgUtil;
import com.upbos.util.CookieUtils;

@RestController
@RequestMapping("upm/login")
public class LoginController {
	private static Logger log = LogManager.getLogger(LoginController.class);
	@Resource
	private SessionManager sessionMgr;
	
	@Resource
	private LoginService srv;
	
	@Resource
	private RandomCodeInterface randomCodeInterface;
	
	@Value("${has.RandomCode}")
	private boolean enableRandomCode = false;
	
	@RequestMapping("needModifyPassword")
	public int needModifyPassword(HttpServletRequest request) {
		Session token = sessionMgr.getSession(request);
		if(token == null) {
			return 0;
		}
		
		return srv.needModifyPassword(token.getUid());
	}
	@RequestMapping("getToken")
	public Session getToken(String tokenId) {
		if(tokenId == null) {
			return null;
		}
		
		return sessionMgr.getSession(tokenId);
	}
	
	@RequestMapping("login")
	public Map<String, Object> login(HttpServletRequest req, HttpServletResponse res, User u, String randomCode) {
		Map<String, Object> r = new HashMap<String, Object>();
		if(enableRandomCode) {
			Cookie c = CookieUtils.findCookieByName(req, "rCode");
			if(c != null && !c.getValue().equals("")) {
				String key = randomCodeInterface.getRandomCode(c.getValue());
				if(!key.equals(randomCode)) {
					r.put("success", false);
					r.put("msg", "验证码错误！");
					return r;
				}
			}else {
				r.put("success", false);
				r.put("msg", "验证码不能为空！");
				return r;
			}
			
		}
		
		if(u.getLoginName() == null || u.getPassword() == null) {
			r.put("success", false);
			r.put("msg", "用户名或密码为空!");
			return r;
		}
		
		User user = srv.queryUserByLoginName(u);
		
		if (user == null) {
			r.put("success", false);
			r.put("msg", "用户名或密码不正确!");
			
		} else if(user.getStatus().equals(User.STATUS_LOGOFF)) {
			r.put("success", false);
			r.put("msg", "用户帐号被注销!");
		} else if(user.getStatus().equals(User.STATUS_FREEZE)) {
			r.put("success", false);
			r.put("msg", "用户帐号被冻结!");
		} else {
			r.put("success", true);
			r.put("msg", "用户登录成功!");
		}
		
		if((boolean)r.get("success")) {
			// 保存当前登录信息
			List<Role> roles = srv.queryUserRoleList(user.getUid());
			Org org = srv.queryOrgById(user.getToOrgId());
			Map<String, Object> attrs = new HashMap<String, Object>();
			attrs.put("org", org);
			attrs.put("roles", roles);
			SessionUser sessionUser = new SessionUser();
			sessionUser.setLoginName(user.getLoginName());
			sessionUser.setName(user.getName());
			sessionUser.setToOrgId(user.getToOrgId());
			sessionUser.setToOrgName(user.getToOrgName());
			//如果用户扩展码为空时，取所属机构代码
			if(user.getExtCode() == null || "".equals(user.getExtCode())) {
				sessionUser.setExtCode(org.getCode());
			}else {
				sessionUser.setExtCode(user.getExtCode());
			}
			sessionUser.setType(user.getType());
			sessionUser.setTypeName(user.getTypeName());
			sessionUser.setUid(user.getUid());
			sessionMgr.login(req, res, sessionUser, attrs);
		}
		
		return r;
	}
	
	@RequestMapping("logout")
	public void logout(HttpServletRequest req, HttpServletResponse res) {
		sessionMgr.logout(req, res);
	}
	
	@RequestMapping("main")
	public Map<String, Object> main(HttpServletRequest req) {
		Session token = sessionMgr.getSession(req);
		if(token == null) {
			log.info("未取得session会话，不能访问主界面！");
			return null;
		}
		
		SessionUser sessionUser = token.getUser();
		Map<String, Object> result = srv.main(sessionUser.getUid());
		result.put("userName", sessionUser.getName());
		
		//首层菜单是否置顶
		String isTopMenu = "true";
		
		//系统名称
		String appTitle = "应用集成开发平台";
		
		List<UserCfg> lst = UserCfgUtil.listUserCfg(sessionUser.getUid(), new String[]{"is_top_menu", "app_title"});
		if(lst != null) {
			for (UserCfg cfg : lst) {
				if (cfg.getKey().equals("is_top_menu")) {
					isTopMenu = cfg.getValue();
				}

				if (cfg.getKey().equals("app_title")) {
					appTitle = cfg.getValue();
				}
			}
		}

		result.put("isTopMenu", isTopMenu);
		result.put("appTitle", appTitle);
		result.put("sysList", srv.listSys());
		return result;
	}
	
	@RequestMapping("modifyPassword")
	public Map<String, Object> modifyPassword(HttpServletRequest request, String oldPassword, String newPassword, String repeatPassword) {
		Session token = sessionMgr.getSession(request);
		
		Map<String, Object> rtn = new HashMap<String, Object>();
		if(token == null) {
			rtn.put("success", false);
			rtn.put("message", "无法获取登录用户信息。");
			return rtn;
		}
		
		/*if(!oldPassword.equals(newPassword)) {
			rtn.put("success", false);
			rtn.put("message", "两次输入的密码不一致。");
			return rtn;
		}*/
		SessionUser sessionUser = token.getUser();
		rtn = srv.modifyPassword(sessionUser.getUid(), oldPassword, newPassword);
		return rtn;
	}
}