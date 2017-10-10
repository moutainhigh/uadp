package com.myzh.framework.web.interceptors;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * 操作日志拦截器
 */
public class AccessInterceptor extends HandlerInterceptorAdapter {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        startLog(request);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);

        // 如果是页面请求，记录返回的页面地址
        // 如果是ajax请求，通过MyResponseBody扩展记录
        if (modelAndView != null) {
            AccessLog actionLog = AccessLog.getCurrentAccessLog();
            actionLog.setResType("url");
            actionLog.setResData(modelAndView.getViewName());
        }
        logger.debug("执行完成");
    }

    //可以根据ex是否为null判断是否发生了异常，进行日志记录。
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request,response,handler,ex);
        endLog(response, ex);
    }

    /**
     * 记录开始日志
     * @param request
     */
    private void startLog(HttpServletRequest request) {
        AccessLog actionLog = AccessLog.getCurrentAccessLog();
        actionLog.setStartTime(new Date());
        actionLog.setReqUrl(request.getRequestURI());
        actionLog.setIp(getIp(request));
        // 请求参数
        Map parameterMap = request.getParameterMap();
        actionLog.setReqParam(JSON.toJSONString(parameterMap));

        logger.debug("请求开始：" + actionLog.toString());
    }

    /**
     * 记录结束日志
     * @param response
     * @param ex
     */
    private void endLog(HttpServletResponse response, Exception ex) {
        AccessLog actionLog = AccessLog.getCurrentAccessLog();
        actionLog.setEndTime(new Date());
        actionLog.setConsumeTime(actionLog.getEndTime().getTime() - actionLog.getStartTime().getTime());
        actionLog.setResStatus(ex == null?String.valueOf(response.getStatus()):"500");
        actionLog.setException(ex == null?"": ex.getStackTrace().toString());
        AccessLog.cleanActionLog();
        logger.debug("请求结束：" + actionLog.toString());
    }

    /**
     * 获取请求ip
     * @param request
     * @return
     */
    private String getIp(HttpServletRequest request) {
        String realIp = request.getHeader("X-Real-IP");
        if (realIp == null || "".equals(realIp)) {
            realIp = request.getHeader("x-forwarded-for");
        }
        if (realIp == null || "".equals(realIp)) {
            realIp = request.getRemoteAddr();
        }
        return realIp;
    }

}
