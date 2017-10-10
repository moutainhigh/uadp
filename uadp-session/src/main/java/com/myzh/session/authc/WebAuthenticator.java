/*******************************************************************************
 * @(#)PcAuthzService.java 2017年04月12日 14:15 
 * Copyright 2017 明医众禾科技（北京）有限责任公司. All rights reserved.
 *******************************************************************************/
package com.myzh.session.authc;

import com.myzh.session.SessionManager;
import com.myzh.session.SessionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <b>Application name：</b> WebAuthcService.java <br>
 * <b>Application describing:</b> <br>
 * <b>Copyright：</b> Copyright &copy; 2017 明医众禾科技（北京）有限责任公司 版权所有。<br>
 * <b>Company：</b> 明医众禾科技（北京）有限责任公司 <br>
 * <b>Date：</b> 2017年04月12日 14:15 <br>
 * <b>author：</b> <a href="mailto:chensg@miyzh.com"> chensg </a>
 * <b>version：</b>V2.0.0
 */
public class WebAuthenticator extends Authenticator {

    Logger log = LoggerFactory.getLogger(WebAuthenticator.class);

    public WebAuthenticator(SessionManager session) {
        super(session);
    }

    /**
     * pc端访问用户身份认证
     * @param req
     * @param res
     * @return
     */
    public boolean doAuthenticate(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String requestUrl = req.getRequestURI();
        log.debug("请求url:{}", requestUrl);

        //session不存在，重新定向session失效页面
        if(!session.validateSession(req)) {
            SessionContext sessionContext = session.getSessionContext();
            sessionContext.redirectSessionExpireUrl(req, res);
            log.info("session过期，重定向url:{}", sessionContext.getSessionExpireUrl());
            return false;
        }
        log.debug("身份认证通过");
        return true;
    }
}