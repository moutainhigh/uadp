/*******************************************************************************
 * @(#)AppSignValidateInterceptor.java 2017年04月15日 11:40 
 * Copyright 2017 明医众禾科技（北京）有限责任公司. All rights reserved.
 *******************************************************************************/
package com.upbos.session.plugin;

import com.alibaba.fastjson.JSON;
import com.upbos.framework.web.ret.RetCode;
import com.upbos.framework.web.ret.RetData;
import com.upbos.session.SessionManager;
import com.upbos.util.encrypt.MD5;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <b>Application name：</b> AppSignValidateInterceptor.java <br>
 * <b>Application describing:</b> <br>
 * <b>Copyright：</b> Copyright &copy; 2017 明医众禾科技（北京）有限责任公司 版权所有。<br>
 * <b>Company：</b> 明医众禾科技（北京）有限责任公司 <br>
 * <b>Date：</b> 2017年04月15日 11:40 <br>
 * <b>author：</b> <a href="mailto:chensg@miyzh.com"> chensg </a>
 * <b>version：</b>V2.0.0
 */
public class AppSignValidateInterceptor implements Interceptor {

    Logger logger = LoggerFactory.getLogger(AppSignValidateInterceptor.class);

    @Override
    public String getInterceptorName() {
        return "appSignValidateInterceptor";
    }

    @Override
    public boolean intercept(HttpServletRequest req, HttpServletResponse res, SessionManager session) throws Exception {
        String tokenId = req.getHeader("session");
        String sign = req.getHeader("sign");
        String time = req.getHeader("time");

        //
        res.setCharacterEncoding("utf-8");
        if (StringUtils.isBlank(sign)) {
            RetData retData = new RetData(RetCode.Common_Sign_Required);
            res.getWriter().write(JSON.toJSONString(retData));
            logger.debug(retData.getMsg());
            return false;
        }
        if (StringUtils.isBlank(time)) {
            RetData retData = new RetData(RetCode.Common_Parameter_Required);
            retData.setMsg(retData.getMsg() + ":时间戳不能为空");
            res.getWriter().write(JSON.toJSONString(retData));
            logger.debug(retData.getMsg());
            return false;
        }

        // 签名校验
        if(sign.equals(MD5.getSignature(tokenId + time, session.getSessionContext().getAppSecretKey()))){
            return true;
        } else {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "未授权");
            RetData retData = new RetData(RetCode.Common_Sign_Error);
            res.getWriter().write(JSON.toJSONString(retData));
            logger.debug(retData.getMsg());
            return false;
        }
    }
}