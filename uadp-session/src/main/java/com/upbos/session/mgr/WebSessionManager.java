package com.upbos.session.mgr;

import com.upbos.session.*;
import com.upbos.session.session.Session;
import com.upbos.session.session.SessionProvider;
import com.upbos.util.HttpUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class WebSessionManager implements SessionManager {

	private static Logger log = LogManager.getLogger(WebSessionManager.class);

	private static ThreadLocal<HttpServletRequest> currentRequest = new ThreadLocal<>();

	private CookieManager cookieManager;

	private SessionContext sessionContext;

	private SessionStorageManager sessionStorageManager;

	private SessionProvider sessionProvider;

	/**
	 * 绑定当前请求
	 * @param request
	 */
	public void bindRequest(HttpServletRequest request) {
		currentRequest.set(request);
	}

	public void clearRequest(HttpServletRequest request) {
		currentRequest.remove();
	}

	public HttpServletRequest getRequest() {
		return currentRequest.get();
	}

	@Override
	public CookieManager getCookieManager() {
		return this.cookieManager;
	}
	
	@Override
	public void setCookieManager(CookieManager cookieMgr) {
		this.cookieManager = cookieMgr;
	}
	
	public void setSessionStorageManager(SessionStorageManager sessionStorageMgr) {
		this.sessionStorageManager = sessionStorageMgr;
	}

	public void setSessionProvider(SessionProvider sessionProvider) {
		this.sessionProvider = sessionProvider;
	}

	public SessionProvider getSessionProvider() {
		return sessionProvider;
	}

	@Override
	public Session login(HttpServletRequest request, HttpServletResponse response, SessionUser user) {
		return login(request, response, user, null);
	}
	
	@Override
	public Session login(HttpServletRequest request, HttpServletResponse response, SessionUser user, Map<String, Object> attrs) {
		Session token = getSession(request);
		// 如果token值与当前登录用户id不一致，先退出原token
		if (!user.isTempUser()) {
			// 登出上次用户登录
			logout(request, response);
			// 登出同一用户
//			logoutSameUser(user.getUid());
			// 清空token
			token = null;
		}

		if (token == null) {
			token = sessionProvider.generateSession(request, sessionContext.getAppSecretKey(), user, attrs);
		} else {
			token.setUser(user);
			token.putAtrr(attrs);
		}
		HttpUtils.allowCross(response);
		HttpUtils.setP3P(response);
		cookieManager.setCookie(request, response, token);
		sessionStorageManager.saveSession(token);
		return token;
	}
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		String tokenId = cookieManager.getTokenId(request);
		sessionStorageManager.removeSession(tokenId);
		cookieManager.removeCookie(request, response);
	}

	/**
	 * 登出同一用户
	 * @param uid
	 */
	public void logoutSameUser(String uid) {
		sessionStorageManager.removeSession(uid, false);
	}

	@Override
	public Session getSession(HttpServletRequest request) {
		String tokenId = cookieManager.getTokenId(request);
		return sessionStorageManager.getSession(tokenId);
	}

	@Override
	public Session getSession() {
		String tokenId = cookieManager.getTokenId(getRequest());
		return sessionStorageManager.getSession(tokenId);
	}

	@Override
	public boolean validateSession(String tokenId) {
		return getSession(tokenId) != null;
	}

	@Override
	public Session refreshSession(Session token) {
		if (token != null) {
			sessionStorageManager.saveSession(token);
		}
		return token;
	}

	@Override
	public boolean validateSession(HttpServletRequest request) {
		String tokenId = cookieManager.getTokenId(request);
		return validateSession(tokenId);
	}

	@Override
	public Session getSession(String tokenId) {
		return sessionStorageManager.getSession(tokenId);
	}
	
	/*@Override
	public Session getTokenByUid(String uid) {
		return sessionStorageManager.getToken(uid);
	}*/
	
	@Override
	public void setAttr(HttpServletRequest request, String key, Object value) {
		Session token = getSession(request);
		if(token == null) {
			log.debug("session操作，未获取有效token。");
			return;
		}
		token.putAttr(key, value);
		sessionStorageManager.saveSession(token);
	}

	@Override
	public <T> T getAttr(HttpServletRequest request, String key) {
		Session token = getSession(request);
		if(token == null) {
			log.debug("session操作，未获取有效token。");
			return null;
		}
		
		return token.getAttr(key);
	}

	@Override
	public void removeAttr(HttpServletRequest request, String key) {
		Session token = getSession(request);
		if(token == null) {
			log.debug("session操作，未获取有效token。");
			return;
		}
		token.removeAtrr(key);
		sessionStorageManager.saveSession(token);
	}

	@Override
	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}

	@Override
	public SessionContext getSessionContext() {
		return sessionContext;
	}
}
