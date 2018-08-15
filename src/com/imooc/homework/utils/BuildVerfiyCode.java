package com.imooc.homework.utils;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class BuildVerfiyCode {

    private static Random random = new Random();

    /**
     * 绘制验证码
     * */
    public static BufferedImage buildCode(int width, int height, HttpServletRequest request) {

        StringBuilder sb = new StringBuilder();  //用于保存验证码

        //生成图片对象，和绘图对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();

        //设置背景颜色
        graphics.setColor(new Color(0xf9,0xf9,0xf9));

        //画出矩形
        graphics.fillRect(0, 0, width, height);

        //画30条干扰线
        for (int i = 0; i < 30; i++) {
            //为每条干扰线设置随机颜色和随机的起止坐标
            graphics.setColor(new Color(random.nextInt(180), random.nextInt(225), random.nextInt(200)));
            graphics.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
        }

        //设置字体
        graphics.setFont(new Font("微软雅黑", Font.BOLD, width / 4));

        //开始绘制4字符，每个字符的颜色随机
        for (int i = 0; i < 4; i++) {
            String drawString = getRandomChar();
            sb.append(drawString);
            graphics.setColor(new Color(random.nextInt(180), random.nextInt(225), random.nextInt(200)));
            graphics.drawString(drawString, i * (width /4)+5,height - (height - (width / 4)) / 2);
        }

        //完成绘制
        graphics.dispose();

        //将验证码放入session
        request.getSession().setAttribute("code", sb.toString());

        return image;
    }

    /**
     * 随机获取字符，供绘图使用
     * */
    private static String getRandomChar() {
        String source = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOPASDFGHJKLZXCVBNM";
        int randomIndex = random.nextInt(source.length());
        return String.valueOf(source.charAt(randomIndex));
    }
}
