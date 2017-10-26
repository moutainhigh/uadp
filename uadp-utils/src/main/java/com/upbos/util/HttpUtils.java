/**
 * Copyright (c) 2011-2014, hubin (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.upbos.util;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * HTTP工具类
 * </p>
 * 
 * @author wangjz
 * @since  2014-5-8
 */
public class HttpUtils {

	private static final Logger logger = Logger.getLogger("HttpUtil");
	
	private final static String encoding = "UTF-8";
	/**
	 * 
	 * 允许 JS 跨域设置
	 * 
	 * <p>
	 * <!-- 使用 nginx 注意在 nginx.conf 中配置 -->
	 * 
	 * http {
  	 * 	......
     * 	 add_header Access-Control-Allow-Origin *;
     *  ......
  	 * } 
	 * </p>
	 * 
	 * <p>
	 * 非 ngnix 下，如果该方法设置不管用、可以尝试增加下行代码。 
	 * 
	 * response.setHeader("Access-Control-Allow-Origin", "*");
	 * </p>
	 * 
	 * @param response
	 * 				响应请求
	 */
	public static void allowJsCrossDomain( HttpServletResponse response ) {
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, POST, PUT, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setHeader("Access-Control-Max-Age", "3600");
	}

	public static void allowCross(HttpServletResponse response) {
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		response.setHeader("Access-Control-Allow-Credentials", "true");
//		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//		response.setHeader("Access-Control-Max-Age", "3600");
//		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

	}

	public static void setP3P(HttpServletResponse response) {
		response.addHeader("P3P", "CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");
	}

	/**
	 * 
	 * <p>
	 * 判断请求是否为 AJAX
	 * </p>
	 * 
	 * @param request
	 * 				当前请求
	 * @return boolean
	 */
	public static boolean isAjax( HttpServletRequest request ) {
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With")) ? true : false;
	}
	
	

	/**
	 * 
	 * <p>
	 * 获取当前 URL 包含查询条件
	 * </p>
	 * 
	 * @param request request
	 * @param encode
	 *            URLEncoder编码格式
	 * @return string
	 * @throws IOException 异常
	 */
	public static String getQueryString(HttpServletRequest request, String encode) throws IOException {
		StringBuffer sb = new StringBuffer(request.getRequestURL());
		String query = request.getQueryString();
		if (query != null && query.length() > 0) {
			sb.append("?").append(query);
		}
		return URLEncoder.encode(sb.toString(), encode);
	}

	

	/**
	 * <p>
	 * URLEncoder 返回地址
	 * </p>
	 * 
	 * @param url
	 *            跳转地址
	 * @param retParam
	 *            返回地址参数名
	 * @param retUrl
	 *            返回地址
	 * @return string
	 */
	public static String encodeRetURL(String url, String retParam, String retUrl) {
		return encodeRetURL(url, retParam, retUrl, null);
	}

	/**
	 * <p>
	 * URLEncoder 返回地址
	 * </p>
	 * 
	 * @param url
	 *            跳转地址
	 * @param retParam
	 *            返回地址参数名
	 * @param retUrl
	 *            返回地址
	 * @param data
	 *            携带参数
	 * @return string
	 */
	public static String encodeRetURL(String url, String retParam, String retUrl, Map<String, String> data) {
		if (url == null) {
			return null;
		}

		StringBuffer retStr = new StringBuffer(url);
		retStr.append("?");
		retStr.append(retParam);
		retStr.append("=");
		try {
			retStr.append(URLEncoder.encode(retUrl, encoding));
		} catch (UnsupportedEncodingException e) {
			logger.severe("encodeRetURL error." + url);
			e.printStackTrace();
		}
		
		if (data != null) {
			for (Map.Entry<String, String> entry : data.entrySet()) {
				retStr.append("&").append(entry.getKey()).append("=").append(entry.getValue());
			}
		}

		return retStr.toString();
	}
	
	/**
	 * <p>
	 * URLDecoder 解码地址
	 * </p>
	 * 
	 * @param url
	 *            解码地址
	 * @return string
	 */
	public static String decodeURL(String url) {
		if (url == null) {
			return null;
		}
		String retUrl = "";
		
		try {
			retUrl = URLDecoder.decode(url, encoding);
		} catch (UnsupportedEncodingException e) {
			logger.severe("encodeRetURL error." + url);
			e.printStackTrace();
		}

		return retUrl;
	}

	/**
	 * <p>
	 * GET 请求
	 * </p>
	 * 
	 * @param request request
	 * @return boolean
	 */
	public static boolean isGet(HttpServletRequest request) {
		if ("GET".equalsIgnoreCase(request.getMethod())) {
			return true;
		}
		return false;
	}

	/**
	 * <p>
	 * POST 请求
	 * </p>
	 * 
	 * @param request request
	 * @return boolean
	 */
	public static boolean isPost(HttpServletRequest request) {
		if ("POST".equalsIgnoreCase(request.getMethod())) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * <p>
	 * 请求重定向至地址 location
	 * </p>
	 * 
	 * @param response
	 * 				请求响应
	 * @param location
	 * 				重定向至地址
	 */
	public static void sendRedirect(HttpServletResponse response, String location) {
		try {
			response.sendRedirect(location);
		} catch (IOException e) {
			logger.severe("sendRedirect location:" + location);
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 获取Request Playload 内容
	 * </p>
	 * 
	 * @param request request
	 * @return Request Playload 内容
	 * @throws  IOException ioException
	 */
	public static String requestPlayload(HttpServletRequest request) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}
		return stringBuilder.toString();
	}

	/**
	 * <p>
	 * 获取当前完整请求地址
	 * </p>
	 * 
	 * @param request request
	 * @return 请求地址
	 */
	public static String getRequestUrl(HttpServletRequest request) {
		StringBuffer url = new StringBuffer(request.getScheme());
		// 请求协议 http,https
		url.append("://");
		// 请求服务器
		String host = request.getHeader("host");
		// 请求url
		String requestUrl = request.getRequestURI();
		// 去掉多余的"/"
		if (host.endsWith("/")) {
			host = host.substring(0, host.length() - 1);
		}
		if (requestUrl.indexOf("////") > -1) {
			requestUrl = requestUrl.replaceAll("////", "//");
		}

		url.append(host).append(requestUrl);
		if (request.getQueryString() != null) {
			// 请求参数
			url.append("?").append(request.getQueryString());
		}
		return url.toString();
	}

	/**
	 * 解析请求参数
	 * @param request 请求
	 * @return map
	 *
	 */
	public static Map<String, String> parseRequestParams(HttpServletRequest request){
		Map<String, String> m = new HashMap<String, String>();
		
		Enumeration<String> names = request.getParameterNames();
        String name = null;
        while(names.hasMoreElements()){
            name = names.nextElement();
            m.put(name, request.getParameter(name));
        }
        
        return m;
	}


	/**
	 * 写响应数据
	 * @param response res
	 * @param content content
	 * @param encoding encoding
	 * @throws IOException 异常
	 */
	public static void writeResponse(HttpServletResponse response, String content, String encoding) throws IOException {
		if (StringUtils.isNotBlank(encoding)) {
			response.setCharacterEncoding(encoding);
		}
		//
		response.getWriter().write(content);
	}

	/**
	 * 获取请求头信息
	 * @param request 请求
	 * @return map
	 */
	public static Map<String, String> getHeaderMap(HttpServletRequest request) {
		Map<String,String> headers = new HashMap<String,String>();
		Enumeration<String> hs = request.getHeaderNames();
		while (hs.hasMoreElements()) {
			String k = hs.nextElement();
			headers.put(k, request.getHeader(k));
		}
		return headers;
	}
}
