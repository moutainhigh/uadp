package com.upbos.session;

import com.upbos.session.session.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CookieManager {
	void setEncrypt(String encrypt);
	void setEncryptKey(String key);
	void setHttpOnly(Boolean httpOnly);
	void setMaxAge(Integer maxAge);
	void setRememberMe(Boolean rememberMe);
	void setSessionIdName(String sid);
	void setCookie( HttpServletRequest request, HttpServletResponse response, Session token) ;
	void removeCookie(HttpServletRequest request, HttpServletResponse response);
	String getTokenId(HttpServletRequest request);
}
