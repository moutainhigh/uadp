/*******************************************************************************
 * @(#)ActionLog.java 2016年12月18日 17:22 
 * Copyright 2016 明医众禾科技（北京）有限责任公司. All rights reserved.
 *******************************************************************************/
package com.myzh.framework.web.interceptors;

import java.util.Date;
import java.util.UUID;

/**
 * <b>Application name：</b> ActionLog.java <br>
 * <b>Application describing:</b> <br>
 * <b>Copyright：</b> Copyright &copy; 2016 明医众禾科技（北京）有限责任公司 版权所有。<br>
 * <b>Company：</b> 明医众禾科技（北京）有限责任公司 <br>
 * <b>Date：</b> 2016年12月18日 17:22 <br>
 * <b>author：</b> <a href="mailto:chensg@miyzh.com"> chensg </a>
 * <b>version：</b>V2.0.0
 */
public class AccessLog {
    public static ThreadLocal<AccessLog> logThreadLocal = new ThreadLocal<>();
    private String id;
    private String tradeNo;
    private String orderNo;
    private String reqUrl;
    private String reqParam;
    private String ip;
    private String resStatus;
    private String resType;
    private String resData;
    private Date startTime;
    private Date endTime;
    private long consumeTime;
    private String exception;

    public AccessLog() {
        id = UUID.randomUUID().toString();
        startTime = new Date();
    }

    public static AccessLog getCurrentAccessLog() {
        AccessLog log = logThreadLocal.get();
        if (log == null) {
            log = new AccessLog();
            logThreadLocal.set(log);
        }
        return log;
    }

    public static void setActionLog(AccessLog log) {
        logThreadLocal.set(log);
    }

    public static void cleanActionLog() {
        logThreadLocal.remove();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getReqUrl() {
        return reqUrl;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }

    public String getReqParam() {
        return reqParam;
    }

    public void setReqParam(String reqParam) {
        this.reqParam = reqParam;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getResStatus() {
        return resStatus;
    }

    public void setResStatus(String resStatus) {
        this.resStatus = resStatus;
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }

    public String getResData() {
        return resData;
    }

    public void setResData(String resData) {
        this.resData = resData;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public long getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(long consumeTime) {
        this.consumeTime = consumeTime;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "ActionLog{" +
                "id='" + id + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", reqUrl='" + reqUrl + '\'' +
                ", reqParam='" + reqParam + '\'' +
                ", ip='" + ip + '\'' +
                ", resStatus='" + resStatus + '\'' +
                ", resType='" + resType + '\'' +
                ", resData='" + resData + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", consumeTime=" + consumeTime +
                ", exception='" + exception + '\'' +
                '}';
    }
}