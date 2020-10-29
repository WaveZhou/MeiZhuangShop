package com.zx.common.yzm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 生成验证码
 */
@WebServlet("/yzm")
public class YzmServlet extends HttpServlet {

    public YzmServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1、生成随机数
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<4;i++) {
			//生成10以内的随机数
			sb.append(random.nextInt(10));
		}
		
		request.getSession().setAttribute("yzm", sb.toString());
		
		//2.创建画布
		BufferedImage image = new BufferedImage(70, 30,BufferedImage.TYPE_INT_RGB);

		//3.创建画笔
		Graphics2D g2d = image.createGraphics();

		//4.先填充一个白色矩形
		//设置画笔颜色
		g2d.setColor(Color.WHITE);
		//填充矩形
		g2d.fillRect(0, 0, 70, 30);


		//5.设置线宽为两个像素
		g2d.setStroke(new BasicStroke(2f));
		//6.在图片上面画一些干扰的元素
		for (int i = 0; i < 200; i++) {
			// 随机颜色
			int r = random.nextInt(255);
			int g = random.nextInt(255);
			int b = random.nextInt(255);
			Color c = new Color(r, g, b);
			g2d.setColor(c);
	
			 // 随机位置（但是干扰点必须在画布以内）
			 int x = random.nextInt(70);
			 int y = random.nextInt(30);
			 //画一条线，两点连成一条线，因为创造的干扰是点，所以两个点的坐标是一样的
			 g2d.drawLine(x, y, x, y);
		}

		//7.设置画笔颜色及字体
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("宋体", Font.BOLD, 24));
		//8.将字写在画布上
		g2d.drawString(sb.toString(), 10, 22);

		//8.设置响应头为image/png，得到一个OutputStream
		response.setContentType("image/png");
		OutputStream out = response.getOutputStream();
		//9.使用ImageIO向OutputStream写出图片，格式png
		ImageIO.write(image, "png", out);

		// 为了保证图片被下载到浏览器，必须flush
		out.flush();
	}

}
