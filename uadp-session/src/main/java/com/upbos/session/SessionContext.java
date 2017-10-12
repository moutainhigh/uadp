package com.upbos.session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upbos.session.plugin.Interceptor;
import com.upbos.session.util.AntPathMatcher;
import com.upbos.util.HttpUtils;

public class SessionContext {
	
	/**
	 * 系统登录地址
	 */
	public String loginUrl;

	/**
	 * 密钥
	 */
	public String appSecretKey;

	/**
	 * session过期失效跳转地址
	 */
	public String sessionExpireUrl;
	
	/**
	 * 不需要session控制的地址
	 */
	public List<String> excludeUrl;
	

	//自定义页面过滤规则
	private List<Interceptor> interceptors;

	// 跨域拦截器
	private List<Interceptor> sysInterceptors = new ArrayList<Interceptor>();


	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getSessionExpireUrl() {
		return sessionExpireUrl;
	}

	public void setSessionExpireUrl(String sessionExpireUrl) {
		this.sessionExpireUrl = sessionExpireUrl;
	}

	public List<String> getExcludeUrl() {
		return excludeUrl;
	}

	public void setExcludeUrl(List<String> excludeUrl) {
		this.excludeUrl = excludeUrl;
	}
	
	public List<Interceptor> getInterceptors() {
		return interceptors;
	}

	public void setInterceptors(List<Interceptor> interceptors) {
		this.interceptors = interceptors;
	}

	public List<Interceptor> getSysInterceptors() {
		return sysInterceptors;
	}

	public void setSysInterceptors(List<Interceptor> sysInterceptors) {
		this.sysInterceptors = sysInterceptors;
	}

	public String getAppSecretKey() {
		return appSecretKey;
	}

	public void setAppSecretKey(String appSecretKey) {
		this.appSecretKey = appSecretKey;
	}

	/**
	 * 重定向请求页面到session过期页面
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	public void redirectSessionExpireUrl(HttpServletRequest req, HttpServletResponse res) throws IOException {
		if(HttpUtils.isAjax(req)) {
			res.getWriter().print("session失效，请重新登陆。");
			return ;
		}
		
		String ctxPath = req.getContextPath();
		if(sessionExpireUrl.toLowerCase().substring(0, 5).equals("http:") || sessionExpireUrl.toLowerCase().substring(0, 6).equals("https:")) {
			res.sendRedirect(sessionExpireUrl);
		}else {
			if(sessionExpireUrl.substring(0, 1).equals("/")) {
				res.sendRedirect(ctxPath + sessionExpireUrl);
			}else {
				res.sendRedirect(ctxPath + "/" + sessionExpireUrl);
			}
		}
		
	}

	/**
	 * 过滤不需要session验证的请求页面，其中包括登录页面和session失效页面
	 * @param contextPath
	 * @param url
	 * @return
	 */
	public boolean excludeFilterUrl(String contextPath, String url) {
		boolean isExclude = false;

		if(url.equals("/") || url.equals(contextPath + "/")) {
			return true;
		}

		if(getExcludeUrl() == null) return isExclude;
		//登录页面和会话超时页面例外
		if(AntPathMatcher.match("/**/" + getLoginUrl(), url) ||
				AntPathMatcher.match("/**/" + getSessionExpireUrl(), url)) {
			return true;
		}

		List<String> excludeUrl = getExcludeUrl();
		for(String eUrl : excludeUrl) {
			if(AntPathMatcher.match("/".equals(contextPath)?eUrl:contextPath + eUrl, url)) {
				isExclude = true;
				break;
			}
		}
		return isExclude;
	}
}
