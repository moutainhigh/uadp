/**
 * Copyright (c) 2011-2014, hubin (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.upbos.util;

import java.util.Random;
import java.util.UUID;

/**
 * <p>
 * 随机数工具类
 * </p>
 * 
 * @author hubin
 * @Date 2014-5-9
 */
public class RandomUtils {

	/**
	 * 
	 * <p>
	 * 生产长度为length的随机字母数字混合字符串
	 * </p>
	 * 
	 * @param length
	 *            指定字符串长度
	 * @return
	 */
	public static String getCharacterAndNumber(int length) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			// 输出字母还是数字
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 字符串
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 取得大写字母还是小写字母
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (choice + random.nextInt(26));
			}
			// 数字
			else if ("num".equalsIgnoreCase(charOrNum)) {
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}

	public static String getNumber(int length) {
		Random rm = new Random();  
	      
	    // 获得随机数  
	    double pross = (1 + rm.nextDouble()) * Math.pow(10, length);  
	  
	    // 将获得的获得随机数转化为字符串  
	    String fixLenthString = String.valueOf(pross);  
	  
	    // 返回固定的长度的随机数  
	    return fixLenthString.substring(1, length + 1);  
	}
	/**
	 * <p>
	 * 获取去掉"-" UUID
	 * </p>
	 */
	public static String get32UUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * <p>
	 * 获取唯一 UUID
	 * </p>
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

}