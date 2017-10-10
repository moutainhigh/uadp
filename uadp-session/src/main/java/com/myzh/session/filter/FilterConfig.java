/*******************************************************************************
 * @(#)PcAuthcConfig.java 2017年04月12日 14:31 
 * Copyright 2017 明医众禾科技（北京）有限责任公司. All rights reserved.
 *******************************************************************************/
package com.myzh.session.filter;


import com.myzh.session.SessionManager;
import com.myzh.session.plugin.Interceptor;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>Application name：</b> FilterConfig.java <br>
 * <b>Application describing:</b> <br>
 * <b>Copyright：</b> Copyright &copy; 2017 明医众禾科技（北京）有限责任公司 版权所有。<br>
 * <b>Company：</b> 明医众禾科技（北京）有限责任公司 <br>
 * <b>Date：</b> 2017年04月12日 14:31 <br>
 * <b>author：</b> <a href="mailto:chensg@miyzh.com"> chensg </a>
 * <b>version：</b>V2.0.0
 */
public class FilterConfig {
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

    // 安全码
    private String secretKey;

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

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}