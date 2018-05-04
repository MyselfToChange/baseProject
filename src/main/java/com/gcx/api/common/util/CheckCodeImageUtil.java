package com.gcx.api.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 生成图片验证码Util
 * 
 * @author lijia
 */
public class CheckCodeImageUtil {

	// 随机生成的字符串的长度
	private int stringLength = 4;

	// 图片的背景色
	private Color bgColor = new Color(240, 240, 180);

	// 干扰线的宽度
	private final int lineWidth = 2;

	// 干扰线的数量
	private final int lineNumber = 100;

	// 字体大小
	private int fontSize = 20;

	// 随机字符串的字体
	private Font font = new Font("Arial", Font.BOLD, fontSize);

	// 随机生成的字符串
	private String randString;

	// 图片宽度
	private int width = 90;

	// 图片高度
	private int height = 25;

	/**
	 * 产生随机前景色,这里的色调基准为深色调
	 * @return 随机颜色
	 */
	private Color createRandColor() {
		Random random = new Random();
		// 红色
		int red = random.nextInt(100);
		// 绿色
		int green = random.nextInt(100);
		// 蓝色
		int blue = random.nextInt(100);
		return new Color(red, green, blue);
	}

	/**
	 * 根据预定义的字符长度,生成随机字符串,规则是字符为数字与大写字母的混合
	 * @return 生成的随机字符串
	 */
	private void createRandString() {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		
		// 生成数字
		for (int i = 0; i < this.stringLength; i++) {
			
			sb.append((char) (random.nextInt(10) + 48));
		}
		
		this.randString = sb.toString();
	}

	/**
	 * 获得随机字符串
	 * @return 随机字符串
	 */
	public String getRandString() {
		return randString;
	}

	/**
	 * 生成图片
	 * @return BufferedImage 
	 */
	public BufferedImage createImage() {

		Random random = new Random();

		// 图片为指定宽度和高度的RGB类型图片
		BufferedImage image = new BufferedImage(this.width, this.height,
				BufferedImage.TYPE_INT_BGR);
		Graphics2D graphics = image.createGraphics();

		// 设置矩形颜色
		graphics.setColor(this.bgColor);

		// 绘制矩形
		graphics.fillRect(0, 0, this.width, this.height);

		// 设置边框颜色
		graphics.setColor(Color.GREEN);

		// 绘制边框
		graphics.drawRect(0, 0, this.width - 1, this.height - 1);

		// 绘制干扰线
		for (int i = 0; i < this.lineNumber; i++) {

			// 设置线条的颜色
			graphics.setColor(this.createRandColor());
			int x = random.nextInt(width - lineWidth - 1) + 1;
			int y = random.nextInt(height - lineWidth - 1) + 1;
			int xl = random.nextInt(lineWidth);
			int yl = random.nextInt(lineWidth);
			graphics.drawLine(x, y, x + xl, y + yl);
		}

		// 产生随机字符串
		this.createRandString();
		graphics.setFont(this.font);

		// 将字符数组转化成字符数组
		char[] chars = this.getRandString().toCharArray();

		for (int i = 0; i < chars.length; i++) {
			graphics.setColor(this.createRandColor());

			String letter = new Character(chars[i]).toString();

			// 这里调整字符的间距和高度,水平偏差为10,高度偏差为15
			graphics.drawString(letter, (random.nextInt(5)
					+ (this.fontSize - 2) * (i + 1) - 5),
					(random.nextInt(5)) + 18);
		}

		// 图片生效
		graphics.dispose();
		return image;
	}
	
}
