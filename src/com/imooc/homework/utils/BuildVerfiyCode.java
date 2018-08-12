package com.imooc.homework.utils;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class BuildVerfiyCode {

    private static Random random = new Random();

    public static BufferedImage buildCode(int width, int height, HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();

        graphics.setColor(new Color(0xf9,0xf9,0xf9));

        graphics.fillRect(0, 0, width, height);

        //画50条干扰线

        for (int i = 0; i < 30; i++) {
            graphics.setColor(new Color(random.nextInt(180), random.nextInt(225), random.nextInt(200)));
            graphics.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
        }


        //画4个字符
        graphics.setFont(new Font("微软雅黑", Font.BOLD, width / 4));
        for (int i = 0; i < 4; i++) {
            String drawString = getRandomChar();
            sb.append(drawString);
            graphics.setColor(new Color(random.nextInt(180), random.nextInt(225), random.nextInt(200)));
            graphics.drawString(drawString, i * (width /4)+5,height - (height - (width / 4)) / 2);
        }
        graphics.dispose();
        System.out.println(sb.toString());

        request.getSession().setAttribute("code", sb.toString());

        return image;
    }

    private static String getRandomChar() {
        String source = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOPASDFGHJKLZXCVBNM";
        int randomIndex = random.nextInt(source.length());
        return String.valueOf(source.charAt(randomIndex));
    }
}
