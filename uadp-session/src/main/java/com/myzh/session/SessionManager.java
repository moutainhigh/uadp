package com.myzh.session;

import com.myzh.session.session.Session;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SessionManager {
	String _token = "sid";

	void bindRequest(HttpServletRequest request);
	Session login(HttpServletRequest request, HttpServletResponse response, SessionUser user);
	Session login(HttpServletRequest request, HttpServletResponse response, SessionUser user, Map<String, Object> attrs);
	void logout(HttpServletRequest request, HttpServletResponse response);
	Session getSession(HttpServletRequest request);
	Session getSession(String tokenId);
	Session getSession();
	Session refreshSession(Session token);
	boolean validateSession(String tokenId);
	boolean validateSession(HttpServletRequest request);
	void setAttr(HttpServletRequest request, String key, Object value);
	<T> T getAttr(HttpServletRequest request, String key);
	void removeAttr(HttpServletRequest request, String key);
	CookieManager getCookieManager();
	void setCookieManager(CookieManager cookieManager);
	void setSessionContext(SessionContext sessionContext);
	SessionContext getSessionContext();
}