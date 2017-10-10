package com.upbos.session.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <b>Title：</b> SSOFilter.java <br>
 * <b>Description： </b> <br>
 * <b>Copyright：</b> Copyright &copy; 2016 明医众禾科技（北京）有限责任公司 版权所有。<br>
 * <b>Company：</b> 明医众禾科技（北京）有限责任公司 <br>
 * <b>Date：</b> 2016年7月17日 <br>
 * <b>author：</b> <a href="mailto:wangjz@miyzh.com"> wangjz </a><br>
 * <b>version：</b> v2.0
 */
public class SessionFilter extends FilterFactoryBean implements Filter {
	private static Logger logger = LogManager.getLogger(SessionFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse)response;
		try {
			if(super.doFilter(req, res)) {
				chain.doFilter(request, response);
			}
		} catch (Exception e) {
			logger.error(e);
			throw new IOException(e);
		}
	}

	@Override
	public void destroy() {
		

	}

}
