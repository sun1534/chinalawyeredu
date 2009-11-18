/**
 * ValidateImageServiceImpl.java
 */

package com.sxit.common.verifycode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author 华锋 2009-1-5  下午03:05:23
 * 
 */

public class ValidateImageServiceImpl implements ValidateImageService {
	
	private static final Log LOG = LogFactory.getLog(ValidateImageServiceImpl.class);
	
	public String createValidateCode(int disturbType, int fontSize, ByteArrayOutputStream bos, int width, int height, String validateCode,
			int codeLength) {
//		System.out.println("disturbType:"+disturbType);
		BufferedImage bImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = bImg.getGraphics();
		Random random = new Random();

		if (null == validateCode || validateCode.equals("")) {
			validateCode = DEFAULT_VALIDATECODE;
		}
		if (fontSize >= height) {
			fontSize = height - 1;
		}

		drawOutline(g, width, height);
		switch (disturbType) {
		case Disturb_Type_Simple:
			drawSimpleDisturb(g, random, width, height);
			break;
		case Disturb_Type_Complex:
			drawDisturb(g, random, width, height);
			break;
		default:
			break;
		}

		String code = drawCode(g, random, validateCode, codeLength, width, height, fontSize);
		g.dispose();
		try {
			ImageOutputStream imOut = ImageIO.createImageOutputStream(bos);
			ImageIO.write(bImg, "JPEG", imOut);
			imOut.close();
		}
		catch (IOException e) {
			LOG.error("验证码生成错误:"+e);
		}
		return code;
	}

	/**
	 * 绘制边框
	 * 
	 * @param g
	 * @param width
	 * @param height
	 */
	private void drawOutline(Graphics g, int width, int height) {
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width - 1, height - 1);
	}

	/**
	 * 绘制比较复杂的干扰线
	 * 
	 * @param g
	 * @param random
	 * @param width
	 * @param height
	 */
	private void drawDisturb(Graphics g, Random random, int width, int height) {
		int x, y, x1, y1;
		for (int i = 0; i < width; i++) {
			x = random.nextInt(width);
			y = random.nextInt(height);
			x1 = random.nextInt(12);
			y1 = random.nextInt(12);
			g.setColor(getRandomColor(random, 120, 255));
			g.drawLine(x, y, x + x1, y + y1);
			g.fillArc(x, y, x1, y1, random.nextInt(360), random.nextInt(360));
		}
	}

	/**
	 * 绘制简单的干扰线
	 * 
	 * @param g
	 * @param random
	 * @param width
	 * @param height
	 */
	private void drawSimpleDisturb(Graphics g, Random random, int width, int height) {
		g.setColor(getRandomColor(random, 160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
	}

	/**
	 * 取得随机颜色
	 * 
	 * @param random
	 * @param pMin
	 * @param pMax
	 * @return
	 */
	private Color getRandomColor(Random random, int pMin, int pMax) {
		pMax = (Math.abs(pMax) > 255 ? 255 : Math.abs(pMax));
		pMin = (Math.abs(pMin) > 255 ? 255 : Math.abs(pMin));

		int r = pMin + random.nextInt(Math.abs(pMax - pMin));
		int g = pMin + random.nextInt(Math.abs(pMax - pMin));
		int b = pMin + random.nextInt(Math.abs(pMax - pMin));

		return new Color(r, g, b);
	}

	/**
	 * 绘制验证码
	 * 
	 * @param g
	 * @param random
	 * @param validateCode
	 * @param codeLength
	 * @param width
	 * @param height
	 * @param fontSize
	 * @return
	 */
	private String drawCode(Graphics g, Random random, String validateCode, int codeLength, int width, int height, int fontSize) {
		int validateCodeLength = validateCode.length();
		Font font1 = new Font("Verdana", Font.BOLD, fontSize);
		Font font2 = new Font("serif", Font.BOLD, fontSize);

		StringBuffer sb = new StringBuffer();
		int x, y;
		for (int i = 0; i < codeLength; i++) {
			x = (width / codeLength - 1) * i + random.nextInt(width / (codeLength * 2));
			y = random.nextInt(height - fontSize) + fontSize;
			sb.append(getRandomChar(validateCode, validateCodeLength, random));
			g.setColor(getRandomColor(random, 70, 150));
			if (sb.substring(i).getBytes().length > 1)
				g.setFont(font2);
			else
				g.setFont(font1);
			g.drawString(sb.substring(i), x, y);
		}
		return sb.toString();
	}

	/**
	 * 取得随机字符
	 * 
	 * @param validateCode
	 * @param validateCodeLength
	 * @param random
	 * @return
	 */
	private char getRandomChar(String validateCode, int validateCodeLength, Random random) {
		return validateCode.charAt(random.nextInt(validateCodeLength));
	}
//	public static void main(String[] args)throws Exception{
//		ValidateImageService validateImageService=new ValidateImageServiceImpl();
//		
//		ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		String validateCode = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789\u4E00\u4E8C\u4E09\u56DB\u4E94\u516D\u4E03\u516B\u4E5D\u5341\u767E\u5343\u4E07\u5E7F\u897F\u5357\u5B81\u79D1\u56ED\u5927\u9053\u8F6F\u4EF6\u4E2D\u56FD\u4EBA\u6C11\u4E07\u5C81";
//		validateCode = validateImageService.createValidateCode(ValidateImageService.Disturb_Type_Complex, 20, bos, 100, 40, validateCode, 5);
////		OutputStream stream = new ByteArrayOutputStream(bos.toByteArray());
//		System.out.println(validateCode);
//		FileOutputStream fos=new FileOutputStream("d:\\"+System.currentTimeMillis()+".jpg");
//		fos.write(bos.toByteArray());
//		fos.flush();
//		fos.close();
//		bos.close();
//		
//	}
}
