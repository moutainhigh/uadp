package com.upbos.upm.randomCode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.upbos.session.SessionManager;
import com.upbos.util.CookieUtils;
import com.upbos.util.RandomUtils;

@Controller
@RequestMapping("/upm")
public class RandomCodeController {

	@Resource
  	private SessionManager sessionMgr;
	
	@Resource
	private RandomCodeInterface randomCodeInterface;
	
	@RequestMapping("/randomCode")
	public void randomCode(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {		// 验证码图片的宽度。
		//生成随机字串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);

		Cookie c = CookieUtils.findCookieByName(req, "rCode");
		String tempRandomCodeKey = "";
		if(c != null && !c.getValue().equals("")) {
			tempRandomCodeKey = c.getValue();
		}else {
			tempRandomCodeKey = "r" + RandomUtils.get32UUID();
			CookieUtils.addCookie(res, "/", "rCode" , tempRandomCodeKey, -1, false, false);
		}
		
		randomCodeInterface.setRandomCode(tempRandomCodeKey, verifyCode);

		int w = 100, h = 30;
		VerifyCodeUtils.outputImage(w, h, res.getOutputStream(), verifyCode);
	}

	public static void main(String[] args) {
		long s = System.currentTimeMillis();
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);

		long e = System.currentTimeMillis();
		System.out.println(e -s);
	}
}
