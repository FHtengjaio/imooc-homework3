package com.imooc.homework.servlet;

import com.imooc.homework.utils.BuildVerfiyCode;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet(name = "VerifyCodeServlet",urlPatterns = "/VerifyCode")
public class VerifyCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedImage image = BuildVerfiyCode.buildCode(40, 20, request);
        ServletOutputStream outputStream = response.getOutputStream();
        boolean write = ImageIO.write(image, "jpg", outputStream);
        outputStream.close();
    }
}
