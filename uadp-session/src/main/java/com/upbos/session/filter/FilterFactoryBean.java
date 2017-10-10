package com.upbos.session.filter;

import com.upbos.session.SessionContext;
import com.upbos.session.SessionManager;
import com.upbos.session.authc.AppAuthenticator;
import com.upbos.session.authc.Authenticator;
import com.upbos.session.authc.WebAuthenticator;
import com.upbos.session.plugin.CrossDomainClientInterceptor;
import com.upbos.session.plugin.CrossDomainServerInterceptor;
import com.upbos.session.plugin.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class FilterFactoryBean implements ApplicationContextAware{
	Logger log = LoggerFactory.getLogger(FilterFactoryBean.class);

	// Spring应用上下文环境
	private static ApplicationContext applicationContext;
		
	//系统登录页面
	private String loginUrl;
	
	//session失效跳转的页面
	private String sessionExpireUrl;
	
	//不需要session验证的页面
	private List<String> excludeUrl;
	
	//自定义页面过滤规则
	private List<Interceptor> interceptors;
	
	private List<Interceptor> sysInterceptors = new ArrayList<Interceptor>();
	
	private SessionManager sessionManager;

	private SessionContext cxt = new SessionContext();

	private String appSecretKey;
	/**
	 * 身份认证器
	 */
	private List<Authenticator> authenticators = new ArrayList<Authenticator>();


	public void setSessionManager(SessionManager session) {
		this.sessionManager = session;
		this.sessionManager.setSessionContext(cxt);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		FilterFactoryBean.applicationContext = applicationContext;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) throws BeansException {
		return (T)applicationContext.getBean(name);
	}


	public void setLoginUrl(String loginUrl) {
		cxt.setLoginUrl(loginUrl);
		this.loginUrl = loginUrl;
	}

	public void setSessionExpireUrl(String sessionExpireUrl) {
		cxt.setSessionExpireUrl(sessionExpireUrl);
		this.sessionExpireUrl = sessionExpireUrl;
	}

	public void setExcludeUrl(List<String> excludeUrl) {
		cxt.setExcludeUrl(excludeUrl);
		this.excludeUrl = excludeUrl;
	}

	public void setAppSecretKey(String appSecretKey) {
		this.appSecretKey = appSecretKey;
		cxt.setAppSecretKey(appSecretKey);
	}

	public List<Interceptor> getInterceptors() {
		return interceptors;
	}

	public void setInterceptors(List<Interceptor> interceptors) {
		this.interceptors = interceptors;
		if(interceptors != null && interceptors.size() > 0) {
			for(int i = 0; i < interceptors.size(); i++) {
				Interceptor ict = interceptors.get(i);
				if(ict instanceof CrossDomainServerInterceptor || ict instanceof CrossDomainClientInterceptor) {
					sysInterceptors.add(ict);
					interceptors.remove(ict);
				}
			}
		}
		cxt.setInterceptors(interceptors);
		cxt.setSysInterceptors(sysInterceptors);
	}



	public boolean doFilter(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 绑定当前请求
		sessionManager.bindRequest(req);

		String requestUrl = req.getRequestURI();
		String contextPath = req.getContextPath();

		if(cxt.excludeFilterUrl(contextPath, requestUrl)) return true;
		//先执行跨域拦截器
		if(sysInterceptors != null && sysInterceptors.size() > 0) {
			for(Interceptor ict : sysInterceptors) {
				if(!ict.intercept(req, res, sessionManager)) {
					return false;
				}
			}
		}

		if (authenticators != null) {
			authenticators = getAuthenticators();
		}

		// 认证是否通过
		boolean isAuthenticated = false;
		// 执行认证器
		for(Authenticator authenticator : authenticators) {
			if (authenticator.getSession() != null) {
				authenticator.setSession(sessionManager);
			}

			if (authenticator.doAuthenticate(req, res)) {
				isAuthenticated = true;
				break;
			}
		}

		if (!isAuthenticated) {
			return isAuthenticated;
		}

		//执行用户自定义拦截器
		if(interceptors != null && interceptors.size() > 0) {
			for(Interceptor ict : interceptors) {
				if(!ict.intercept(req, res, sessionManager)) {
					log.debug("拦截器：{}发生拦截。", ict.getInterceptorName());
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * 设置认证器
	 * @param authenticators
	 */
	public void setAuthenticators(List<Authenticator> authenticators) {
		if (authenticators != null && !authenticators.isEmpty()) {
			this.authenticators = authenticators;
		}
	}

	/**
	 * 获取身份认证服务
	 * @param req
	 * @param res
	 * @return
	 */
	private List<Authenticator> getAuthenticators() {
		if (authenticators.isEmpty()) {
			authenticators.add(new AppAuthenticator(sessionManager));
			authenticators.add(new WebAuthenticator(sessionManager));
		}
		return authenticators;
	}
}