package com.upbos.util;

import org.junit.Test;

import com.upbos.util.encrypt.UadpSymmetrical;

public class TestEncrypt {
	private static String secretKey = "1234567890";
	@Test
	public void encrypt() {
		UadpSymmetrical en = new UadpSymmetrical();
		try {
			String content = en.encrypt("大大大是打算到拉萨的骄傲的设计案例的设计案例涉及到辣椒水啦啦啦啦所多啦啦啦啦多军多军多军多军军奥斯卡大家阿萨德阿斯顿撒旦卡斯柯氨基酸打卡机SD卡圣诞节卡死机多砍就阿萨科技卡手机打开加速度按时", secretKey);
			System.out.println("加密后内容：" + content);
			String decryptContent = en.decrypt(content, secretKey + "sdfsdfsdf");
			System.out.println("解密后内容：" + decryptContent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
