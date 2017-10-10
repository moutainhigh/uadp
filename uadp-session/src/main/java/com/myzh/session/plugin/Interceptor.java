package com.myzh.session.plugin;

import com.myzh.session.SessionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Interceptor {

	String getInterceptorName();
	boolean intercept(HttpServletRequest req, HttpServletResponse res, SessionManager session) throws Exception;
}