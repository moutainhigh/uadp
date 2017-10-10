/*******************************************************************************
 * @(#)RetData.java 2017年04月13日 10:18 
 * Copyright 2017 明医众禾科技（北京）有限责任公司. All rights reserved.
 *******************************************************************************/
package com.myzh.framework.web.ret;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>Application name：</b> RetData.java <br>
 * <b>Application describing:</b> <br>
 * <b>Copyright：</b> Copyright &copy; 2017 明医众禾科技（北京）有限责任公司 版权所有。<br>
 * <b>Company：</b> 明医众禾科技（北京）有限责任公司 <br>
 * <b>Date：</b> 2017年04月13日 10:18 <br>
 * <b>author：</b> <a href="mailto:chensg@miyzh.com"> chensg </a>
 * <b>version：</b>V2.0.0
 */
public class RetData implements Serializable {
    private boolean success = true;
    private String code = "";
    private String msg = "";
    private Object data = new HashMap<String, Object>();


    public RetData() {
    }
    public RetData(RetCode retCode) {
        setRetCode(retCode);
    }

    public void putData(String key, Object value) {
        if (data instanceof Map) {
            ((Map<String, Object>)data).put(key, value);
        }
    }

    public Object getData(String key) {
        if (data instanceof Map) {
            return ((Map<String, Object>) data).get(key);
        }
        return data;
    }

    public void removeData(String key) {
        if (data instanceof Map) {
            ((Map<String, Object>)data).remove(key);
        }
    }

    /**
     * 清除数据
     */
    public void clearData() {
        if (data instanceof Map) {
            ((Map<String, Object>)data).clear();
        }
    }
    public void setRetCode(RetCode retCode) {
        this.code = retCode.getCode();
        this.msg = retCode.getMsg();
        this.setSuccess(RetCode.SUCCESS.getCode().equalsIgnoreCase(this.code)?true:false);
    }
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RetData{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}