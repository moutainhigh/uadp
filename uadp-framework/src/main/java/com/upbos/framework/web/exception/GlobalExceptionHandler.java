/*******************************************************************************
 * @(#)GlobalExceptionHandler.java 2016年07月29日 16:21
 * Copyright 2016 明医众禾科技（北京）有限责任公司. All rights reserved.
 *******************************************************************************/
package com.upbos.framework.web.exception;

import com.upbos.framework.web.ret.RetCode;
import com.upbos.framework.web.ret.RetData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Locale;

/**
 * <b>Application name：</b> GlobalExceptionHandler.java <br>
 * <b>Application describing:</b> 全局异常处理器<br>
 * <b>Copyright：</b> Copyright &copy; 2016 明医众禾科技（北京）有限责任公司 版权所有。<br>
 * <b>Company：</b> 明医众禾科技（北京）有限责任公司 <br>
 * <b>Date：</b> 2016年07月29日 16:21 <br>
 * <b>author：</b> <a href="mailto:chensg@miyzh.com"> chensg </a> <b>version：</b>V2.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(com.upbos.framework.web.exception.GlobalExceptionHandler.class);

    @Autowired
    MessageSource messageSource;

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleControllerException(Exception exception) {
        logger.error("全局异常：", exception);
        if (exception instanceof BindingResult) {
            return onBindError((BindingResult) exception);
        } else if (exception instanceof ServletRequestBindingException) {
            return onRequestBindError(exception);
        } else {
            logger.error("Error occurs:", exception);
        }
        exception.printStackTrace();
        RetData resData = new RetData();
        resData.setRetCode(RetCode.Unknown_Exception);
        return new ResponseEntity<Object>(resData, HttpStatus.OK);
    }

    /**
     * 请求参数绑定错误
     * @param ex
     * @return
     */
    private ResponseEntity<Object> onRequestBindError(Exception ex) {
        RetData resData = new RetData();
        resData.setRetCode(RetCode.Common_Parameter_Required);
        resData.setMsg(ex.getMessage());
        return new ResponseEntity<Object>(resData, HttpStatus.OK);
    }

    /**
     * 请求参数绑定错误
     * @param bindingResult
     * @return
     */
    private ResponseEntity<Object> onBindError(BindingResult bindingResult) {
        List<FieldError> errorList = bindingResult.getFieldErrors();
        String message = "";
        if (errorList != null && errorList.size() > 0) {
            for(FieldError error : errorList) {
                String code = error.getDefaultMessage();
                message += messageSource.getMessage(code, error.getArguments(), Locale.CHINA) + ";";
                logger.error("binding parameter error, code={}, message={}", code, message);
            }
        }
        RetData resData = new RetData();
        resData.setRetCode(RetCode.Common_Parameter_Required);

        return new ResponseEntity<Object>(resData, HttpStatus.OK);
    }
}
