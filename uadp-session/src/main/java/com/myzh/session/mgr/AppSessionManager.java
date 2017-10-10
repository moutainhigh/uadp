/*******************************************************************************
 * @(#)AppSessionManager.java 2017年04月14日 20:12 
 * Copyright 2017 明医众禾科技（北京）有限责任公司. All rights reserved.
 *******************************************************************************/
package com.myzh.session.mgr;

import com.myzh.session.*;
import com.myzh.session.session.Session;
import com.myzh.session.session.SessionProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <b>Application name：</b> AppSessionManager.java <br>
 * <b>Application describing:</b> <br>
 * <b>Copyright：</b> Copyright &copy; 2017 明医众禾科技（北京）有限责任公司 版权所有。<br>
 * <b>Company：</b> 明医众禾科技（北京）有限责任公司 <br>
 * <b>Date：</b> 2017年04月14日 20:12 <br>
 * <b>author：</b> <a href="mailto:chensg@miyzh.com"> chensg </a>
 * <b>version：</b>V2.0.0
 */
public class AppSessionManager implements SessionManager {

    private static Logger log = LogManager.getLogger(AppSessionManager.class);

    private static ThreadLocal<HttpServletRequest> currentRequest = new ThreadLocal<>();

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
    public Session login(HttpServletRequest request, HttpServletResponse response, SessionUser user) {
        return login(request, response, user, null);
    }

    @Override
    public Session login(HttpServletRequest request, HttpServletResponse response, SessionUser user, Map<String, Object> attrs) {
        Session token = sessionProvider.generateSession(request, sessionContext.getAppSecretKey(), user, attrs);
        sessionStorageManager.saveSession(token);
        return token;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Session token = new Session();
        token.setId(request.getHeader("token"));
        sessionStorageManager.removeSession(token.getId());
    }

    @Override
    public Session getSession(HttpServletRequest request) {
        String tokenId = request.getHeader("token");
        return sessionStorageManager.getSession(tokenId);
    }

    @Override
    public Session getSession(String tokenId) {
        return sessionStorageManager.getSession(tokenId);
    }

    @Override
    public Session getSession() {
        return getSession(getRequest());
    }

    @Override
    public Session refreshSession(Session token) {
        return token;
    }

    @Override
    public boolean validateSession(String tokenId) {
        return sessionProvider.validateId(tokenId, sessionContext.getAppSecretKey());
    }

    @Override
    public boolean validateSession(HttpServletRequest request) {
        return validateSession(getSession(request).getId());
    }

    @Override
    public void setAttr(HttpServletRequest request, String key, Object value) {

    }

    @Override
    public <T> T getAttr(HttpServletRequest request, String key) {
        return null;
    }

    @Override
    public void removeAttr(HttpServletRequest request, String key) {

    }

    @Override
    public CookieManager getCookieManager() {
        return null;
    }

    @Override
    public void setCookieManager(CookieManager cookieManager) {
    }

    @Override
    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    @Override
    public SessionContext getSessionContext() {
        return sessionContext;
    }

    public SessionStorageManager getSessionStorageManager() {
        return sessionStorageManager;
    }

    public void setSessionStorageManager(SessionStorageManager sessionStorageManager) {
        this.sessionStorageManager = sessionStorageManager;
    }

    public SessionProvider getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(SessionProvider sessionProvider) {
        this.sessionProvider = sessionProvider;
    }
}