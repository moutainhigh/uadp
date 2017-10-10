package com.myzh.upm.randomCode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
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

import com.myzh.session.SessionManager;
import com.myzh.util.CookieUtils;
import com.myzh.util.RandomUtils;

@Controller
@RequestMapping("/upm")
public class RandomCodeController {

	@Resource
  	private SessionManager sessionMgr;
	
	@Resource
	private RandomCodeInterface randomCodeInterface;
	
	@RequestMapping("/randomCode")
	public void randomCode(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {		// 验证码图片的宽度。
		int width = 60;
		// 验证码图片的高度。
		int height = 30;
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();

		// 创建一个随机数生成器类。
		Random random = new Random();

		// 设定图像背景色(因为是做背景，所以偏淡)
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font("Times New Roman", Font.HANGING_BASELINE, 22);
		// 设置字体。
		g.setFont(font);
		// 画边框。
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width - 1, height - 1);
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到。
		// g.setColor(Color.GRAY);
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();
		// 设置默认生成4个验证码
		int length = 4;
		// 设置备选验证码:包括"a-z"和数字"0-9"
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		int size = base.length();
		// 随机产生4位数字的验证码。
		for (int i = 0; i < length; i++) {
			// 得到随机产生的验证码数字。
			int start = random.nextInt(size);
			String strRand = base.substring(start, start + 1);

			// 用随机产生的颜色将验证码绘制到图像中。
			// 生成随机颜色(因为是做前景，所以偏深)
			// g.setColor(getRandColor(1, 100));

			// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));

			g.drawString(strRand, 15 * i + 2, 22);

			// 将产生的四个随机数组合在一起。
			randomCode.append(strRand);
		}
		// 将四位数字的验证码保存到Session中。
		/*HttpSession session = request.getSession();
		session.setAttribute("rand", randomCode.toString());*/
		//sessionSrv.login(req, res, null, null);
		
		Cookie c = CookieUtils.findCookieByName(req, "randomCodeKey");
		String tempRandomCodeKey = "";
		if(c != null && !c.getValue().equals("")) {
			tempRandomCodeKey = c.getValue();
		}else {
			tempRandomCodeKey = "randomCode" + RandomUtils.get32UUID();
			CookieUtils.addCookie(res, "/", "randomCodeKey" , tempRandomCodeKey, -1, false, false);
		}
		
		randomCodeInterface.setRandomCode(tempRandomCodeKey, randomCode.toString());
		
		// 图象生效
		g.dispose();
		// 禁止图像缓存。
		res.setHeader("Pragma", "no-cache");
		res.setHeader("Cache-Control", "no-cache");
		res.setDateHeader("Expires", 0);
		res.setContentType("image/jpeg");
		// 将图像输出到Servlet输出流中。
		ServletOutputStream sos = res.getOutputStream();
		ImageIO.write(buffImg, "jpeg", sos);
		sos.flush();
		sos.close();
	}

	Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
