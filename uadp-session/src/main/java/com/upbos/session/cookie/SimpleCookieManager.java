package com.upbos.session.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upbos.session.SessionManager;
import com.upbos.session.session.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.upbos.session.CookieManager;
import com.upbos.util.CookieUtils;
import com.upbos.util.encrypt.Algorithm;
import com.upbos.util.encrypt.UadpSymmetrical;

public class SimpleCookieManager implements CookieManager {

	private static Logger log = LogManager.getLogger(SimpleCookieManager.class);
	
	private boolean httpOnly = false;
	
	private int maxAge = -1;
	
	private boolean rememberMe = false;
	
	private String sessionIdName = SessionManager._token;//"sid";
	
	private UadpSymmetrical symmetrical = new UadpSymmetrical(); 
	
	private String encryptKey = "4uq2al231f";
	
	@Override
	public void setHttpOnly(Boolean httpOnly) {
		this.httpOnly = httpOnly;
	}

	@Override
	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}

	@Override
	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	@Override
	public void setSessionIdName(String sid) {
		this.sessionIdName = sid;
	}

	
	@Override
	public void setCookie(HttpServletRequest request, HttpServletResponse response, Session token) {
		String sid = token.getId();
//		try {
//			sid = symmetrical.encrypt(token.getId(), this.encryptKey);
//		} catch (Exception e) {
//			log.error(e);
//		}
		CookieUtils.addCookie(response, "/", this.sessionIdName , sid, this.maxAge, this.httpOnly, false);
	}

	@Override
	public void removeCookie(HttpServletRequest request, HttpServletResponse response) {
		CookieUtils.clearCookieByName(request, response, this.sessionIdName);
	}

	@Override
	public String getTokenId(HttpServletRequest request) {
		Cookie cookie = CookieUtils.findCookieByName(request, this.sessionIdName);
		if(cookie == null) return null;
//		try {
//			String tokenId = symmetrical.decrypt(cookie.getValue(), this.encryptKey);
//			return tokenId;
//		} catch (Exception e) {
//			log.error(e);
//		}
		return cookie.getValue();

	}
	
	/*@Override
	public String getUid(HttpServletRequest request) {
		Cookie cookie = CookieUtils.findCookieByName(request, this.sessionIdName);
		if(cookie == null) return null;
		try {
			String sessionId = symmetrical.decrypt(cookie.getValue(), this.encryptKey);
			return parseCookieValue(sessionId);
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}*/
	
	/*@Override
	public String getUid(String sessionId) {
		try {
			String v = symmetrical.decrypt(sessionId, this.encryptKey);
			return parseCookieValue(v);
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}*/

	/*@Override
	public String getSessionId(HttpServletRequest request) {
		Cookie cookie = CookieUtils.findCookieByName(request, this.sessionIdName);
		if(cookie == null) return null;
		return cookie.getValue();
	}*/
	
	
	@Override
	public void setEncrypt(String encrypt) {
		symmetrical = new UadpSymmetrical(Algorithm.convert(encrypt));
	}
	
	public void setEncryptKey(String key) {
		this.encryptKey = key;
	}
	
}
