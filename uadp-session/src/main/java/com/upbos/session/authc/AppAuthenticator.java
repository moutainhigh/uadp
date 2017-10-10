/*******************************************************************************
 * @(#)AppAuthzService.java 2017年04月12日 14:15 
 * Copyright 2017 明医众禾科技（北京）有限责任公司. All rights reserved.
 *******************************************************************************/
package com.upbos.session.authc;

import com.alibaba.fastjson.JSON;
import com.upbos.framework.web.ret.RetCode;
import com.upbos.framework.web.ret.RetData;
import com.upbos.session.SessionManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <b>Application name：</b> AppAuthcService.java <br>
 * <b>Application describing:</b> <br>
 * <b>Copyright：</b> Copyright &copy; 2017 明医众禾科技（北京）有限责任公司 版权所有。<br>
 * <b>Company：</b> 明医众禾科技（北京）有限责任公司 <br>
 * <b>Date：</b> 2017年04月12日 14:15 <br>
 * <b>author：</b> <a href="mailto:chensg@miyzh.com"> chensg </a>
 * <b>version：</b>V2.0.0
 */
public class AppAuthenticator extends Authenticator {

    Logger log = LoggerFactory.getLogger(AppAuthenticator.class);

    private final String TOKEN = "token";

    public AppAuthenticator(SessionManager session) {
        super(session);
    }


    /**
     * 用户身份验证
     * @param req
     * @param res
     * @return
     * @throws Exception
     */
    public boolean doAuthenticate(HttpServletRequest req, HttpServletResponse res) throws Exception {

        Map<String, Object> headers = getHeader(req);
        log.debug("headers:{}",headers);

        // 检测token
        String tokenId = (String) headers.get(TOKEN);

        RetData retData = new RetData(RetCode.SUCCESS);
        if (StringUtils.isBlank(tokenId)) {
            retData.setRetCode(RetCode.Common_Token_Required);
        }

        // 验证
        if (retData.isSuccess()) {
            // session 验证
            boolean isOk = session.validateSession(tokenId);
            if (isOk) {
                log.info("APP 身份验证成功，session：{}", tokenId);
                return true;
            }
        }

        // 验证失败
        retData.setRetCode(RetCode.Common_Token_Authority_Error);
        log.error("APP 身份验证失败,session:{},失败原因：{}", tokenId, retData.toString());
        res.getWriter().write(JSON.toJSONString(retData));
        return false;
    }


}