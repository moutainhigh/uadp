package com.upbos.session.plugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upbos.session.session.Session;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.upbos.session.SessionManager;
import com.upbos.session.util.AntPathMatcher;
import com.upbos.util.HttpUtils;
import org.apache.tools.ant.taskdefs.condition.Http;

import java.net.URLEncoder;

public class CrossDomainServerInterceptor implements Interceptor {

	private static final Logger log = LogManager.getLogger(CrossDomainServerInterceptor.class);
	/**
	 * 跨域登录验证服务器地址 
	 */
	private String validateServerUrl = "/**/session/validateToken.do";

	public void setValidateServerUrl(String validateServerUrl) {
		this.validateServerUrl = validateServerUrl;
	}


	@Override
	public boolean intercept(HttpServletRequest req, HttpServletResponse res, SessionManager session) throws Exception {
		//如果配置了validateServerUrl参数，则验证匹配validateServerUrl参数地址
		String requestUrl = req.getRequestURI();
		log.debug("收到跨域登录验证请求：{}，设置允许跨域：allowCross", requestUrl);
		// 允许跨域
		HttpUtils.allowCross(res);
		if(isValidateUrl(requestUrl)) {
			String returnUrl = req.getParameter("returnUrl");
			log.debug("正在单点登录，回调returnUrl：{}", returnUrl);
			if(returnUrl == null) {
				log.debug("无效登录单点登录请求回调参数：{}", returnUrl);
				return false;
			}
			
			Session token = session.getSession(req);
			if(token == null) {
				String loginUrl = session.getSessionContext().loginUrl;
				if (StringUtils.isNotBlank(returnUrl)) {
					loginUrl = loginUrl + (loginUrl.indexOf("?") > -1?"&":"?") + "returnUrl=" + URLEncoder.encode(returnUrl, "utf-8");
				}
				log.debug("单点登录结果为：未登录，重定向登录url：{}", loginUrl);
				res.sendRedirect(loginUrl);
				return false;
			}
			
			StringBuffer returnUrlBuffer = new StringBuffer(returnUrl);
			//解码
			returnUrl = HttpUtils.decodeURL(returnUrl);
			
			if(returnUrl.indexOf("?") != -1) {
				returnUrlBuffer.append("&").append(SessionManager._token).append("=").append(token.getId());
			}else {
				returnUrlBuffer.append("?").append(SessionManager._token).append("=").append(token.getId());
			}

			returnUrl = returnUrlBuffer.toString();
			log.debug("单点登录结果为：已登录，重定向returnUrl：{}", returnUrl);
			res.sendRedirect(returnUrl);
			return false;
		} else {
			Session token = session.getSession(req);
			//session不存在，重新定向session失效页面
			if(token == null ) {
				if(HttpUtils.isAjax(req)) {
					session.getSessionContext().redirectSessionExpireAjax(req, res);
				}else {
					session.getSessionContext().redirectSessionExpireUrl(req, res);
					log.info("session过期，重定向url:{}", session.getSessionContext().sessionExpireUrl);
				}
				return false;
			}
			log.debug("登录验证成功");
		}
		return true;
	}

	@Override
	public String getInterceptorName() {
		return "跨域验证拦截器";
	}

	/**
	 * 是否是认证请求url
	 * @param requestUrl
	 * @return
	 */
	private boolean isValidateUrl(String requestUrl) {
		boolean isValidateUrl = false;
		if (StringUtils.isNotBlank(this.validateServerUrl)) {
			String[] validateServerUrlArray = this.validateServerUrl.split(",");
			for (String validateServerUrl:validateServerUrlArray) {
				if(AntPathMatcher.match(validateServerUrl, requestUrl, true)) {
					isValidateUrl = true;
					break;
				}
			}
		} else {
			log.error("未配置验证url:validateServerUrl");
		}

		return isValidateUrl;
	}
}
