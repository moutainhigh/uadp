/*******************************************************************************
 * @(#)Authc.java 2017年04月12日 14:41 
 * Copyright 2017 明医众禾科技（北京）有限责任公司. All rights reserved.
 *******************************************************************************/
package com.upbos.session.authc;

import com.upbos.session.SessionManager;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>Application name：</b> Authenticator.java <br>
 * <b>Application describing:</b> <br>
 * <b>Copyright：</b> Copyright &copy; 2017 明医众禾科技（北京）有限责任公司 版权所有。<br>
 * <b>Company：</b> 明医众禾科技（北京）有限责任公司 <br>
 * <b>Date：</b> 2017年04月12日 14:41 <br>
 * <b>author：</b> <a href="mailto:chensg@miyzh.com"> chensg </a>
 * <b>version：</b>V2.0.0
 */
public abstract class Authenticator {

    /**
     * 过滤器参数
     */
    protected SessionManager session;

    public Authenticator(SessionManager session) {
        this.session = session;
    }

    public SessionManager getSession() {
        return session;
    }

    public void setSession(SessionManager session) {
        this.session = session;
    }

    /**
     * 身份认证
     * @param req
     * @param res
     * @return
     * @throws Exception
     */
    public abstract boolean doAuthenticate(HttpServletRequest req, HttpServletResponse res) throws Exception;

    /**
     * 获取请求头信息
     * @param request
     * @return
     */
    public Map<String,Object> getHeader(HttpServletRequest request) {
        Map<String,Object> headers = new HashMap<String,Object>();
        Enumeration<String> hs = request.getHeaderNames();
        while (hs.hasMoreElements()) {
            String k = hs.nextElement();
            headers.put(k, request.getHeader(k));
        }
        return headers;
    }



    /**
     * 获取请求ip
     * @param request
     * @return
     */
    protected String getIp(HttpServletRequest request) {
        String realIp = request.getHeader("X-Real-IP");
        if (StringUtils.isBlank(realIp)) {
            realIp = request.getHeader("x-forwarded-for");
        }
        if (StringUtils.isBlank(realIp)) {
            realIp = request.getRemoteAddr();
        }
        return realIp;
    }
}