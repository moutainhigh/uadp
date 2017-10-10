package com.upbos.session.plugin;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.upbos.session.SessionManager;
import com.upbos.session.session.Session;
import com.upbos.util.HttpUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CrossDomainClientInterceptor implements Interceptor {

	private static final Logger log = LogManager.getLogger(CrossDomainClientInterceptor.class);
	
	/**
	 * 跨域登录验证服务器地址 
	 */
	private String validateServerUrl;

	public void setValidateServerUrl(String validateServerUrl) {
		this.validateServerUrl = validateServerUrl;
	}

	@Override
	public boolean intercept(HttpServletRequest req, HttpServletResponse res, SessionManager cxt) throws Exception {
		// 请求url
		String requestUrl = req.getRequestURI();
		log.debug("验证当前请求url：{}登录状态，设置允许跨域：allowCross", requestUrl);
		// 允许跨域
		HttpUtils.allowCross(res);
		//如果请求地址带有参数_token，则验证token的有效性，并过滤掉_token参数
		String tokenId = req.getParameter(SessionManager._token);
		if(tokenId == null) {
			//如果token无效，并且配置了跨域登录验证地址
			if(cxt.getSession(req) == null && validateServerUrl != null) {
				String returnUrl = HttpUtils.getRequestUrl(req);
				log.debug("当前请求未登录，重定向单点登录url：{},回调url:{}", validateServerUrl, returnUrl);
				String redirectUrl = HttpUtils.encodeRetURL(validateServerUrl, "returnUrl", returnUrl);
				res.sendRedirect(redirectUrl); 
				return false;
			}
			return true;
		} else {
			log.debug("请求地址带有参数token信息:{},验证token并写入Cookie", tokenId);
			Session token = cxt.getSession(tokenId);
			if(token != null) {
				log.debug("token验证成功,user:{}", JSON.toJSONString(token.getUser()));
				cxt.getCookieManager().setCookie(req, res, token);
				redirect302Url(req, res);
			}else {
				log.debug("token验证失败，重定向session过期url:{}", cxt.getSessionContext().getSessionExpireUrl());
				cxt.getSessionContext().redirectSessionExpireUrl(req, res);
			}
			return false;
		}
	}
	
	
	/**
	 * 重定向请求页面，过滤掉参数:_token
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	private void redirect302Url(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String url = req.getRequestURI();
		String params = "";
		Enumeration<String> paramNames = req.getParameterNames();  
        while (paramNames.hasMoreElements()) {  
            String paramName = paramNames.nextElement();  
  
            String[] paramValues = req.getParameterValues(paramName);  
            if (paramValues.length == 1) {  
                String paramValue = paramValues[0];  
                if (paramValue.length() != 0) {
                	if(paramName.equals(SessionManager._token)) continue;
                	params += ("&" + paramName + "=" + paramValue);
                }
            }  
        }
        if(!params.equals("")) {
        	params = "?" + params.substring(1);
        }
        log.debug("重定向请求页面:{}, 参数", url, params);
		res.sendRedirect(url +  params);
	}


	@Override
	public String getInterceptorName() {
		return "跨域验证拦截器";
	}
}
