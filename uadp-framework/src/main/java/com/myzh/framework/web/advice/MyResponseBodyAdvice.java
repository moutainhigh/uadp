/*******************************************************************************
 * @(#)MyResponseBodyAdvice.java 2016年12月18日 17:19 
 * Copyright 2016 明医众禾科技（北京）有限责任公司. All rights reserved.
 *******************************************************************************/
package com.myzh.framework.web.advice;

import com.alibaba.fastjson.JSON;
import com.myzh.framework.web.interceptors.AccessLog;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.net.URLDecoder;

/**
 * <b>Application name：</b> MyResponseBodyAdvice.java <br>
 * <b>Application describing:</b> <br>
 * <b>Copyright：</b> Copyright &copy; 2016 明医众禾科技（北京）有限责任公司 版权所有。<br>
 * <b>Company：</b> 明医众禾科技（北京）有限责任公司 <br>
 * <b>Date：</b> 2016年12月18日 17:19 <br>
 * <b>author：</b> <a href="mailto:chensg@miyzh.com"> chensg </a>
 * <b>version：</b>V2.0.0
 */
//@ControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object responseBody, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        try {
            // 更新日志的返回结果
            String responseStr = JSON.toJSONString(responseBody);
            responseStr = URLDecoder.decode(responseStr, "utf-8");
            AccessLog actionLog = AccessLog.getCurrentAccessLog();
            if (actionLog != null) {
                actionLog.setResData(responseStr);
                actionLog.setResType(mediaType.getType());
                AccessLog.setActionLog(actionLog);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseBody;
    }
}