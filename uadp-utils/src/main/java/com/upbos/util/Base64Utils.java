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

import com.upbos.util.base64.Base64;
import com.upbos.util.base64.UrlBase64;

/**
 * <p>
 * BASE64编码解码工具包
 * </p>
 * <p>
 * 依赖bcprov-jdk14-1.48.jar
 * </p>
 * 
 * @author hubin
 * @since 2014-6-17
 */
public class Base64Utils {

	private static String encoding = "UTF-8";
	/**
	 * <p>
	 * BASE64字符串解码为二进制数据
	 * </p>
	 * 
	 * @param base64 base64
	 * @return byte[]
	 * @throws Exception 异常
	 */
	public static byte[] decode(String base64) throws Exception {
		return Base64.decode(base64.getBytes());
	}

	/**
	 * <p>
	 * 二进制数据编码为BASE64字符串
	 * </p>
	 * 
	 * @param bytes bytes
	 * @return string
	 * @throws Exception 异常
	 */
	public static String encode(byte[] bytes) throws Exception {
		return new String(Base64.encode(bytes));
	}

	/**
	 * BASE64 encrypt
	 * 
	 * @param key byte[]
	 * @return string
	 * @throws Exception 异常
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		byte[] b = UrlBase64.encode(key);
		return new String(b, encoding);
	}

	/**
	 * BASE64 decrypt
	 * 
	 * @param key string
	 * @return byte[]
	 * @throws Exception 异常
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return UrlBase64.decode(key.getBytes(encoding));
	}

}
