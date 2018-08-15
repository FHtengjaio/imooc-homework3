package com.imooc.homework.servlet;

import com.imooc.homework.utils.BuildVerfiyCode;
import com.imooc.homework.utils.StringUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "VerifyCodeServlet",urlPatterns = {"/VerifyCode", "/CheckCode"})
public class VerifyCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Objects.equals("/VerifyCode", request.getServletPath())) {
            BufferedImage image = BuildVerfiyCode.buildCode(98, 48, request); //获取绘制的验证码图片
            //将验证码通过流传输给页面
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(image, "jpg", outputStream);
            outputStream.close();
        } else if (Objects.equals("/CheckCode", request.getServletPath())) {
            //只响应XMLHttpRequest请求
            if (request.getHeader("x-requested-with") == null) {
                response.sendRedirect("/Home");
                return;
            }
            //获取用户提交的code，与session中保存的code比较，忽略大小写
            String code = request.getParameter("code");
            ServletOutputStream outputStream = response.getOutputStream();
            if (code != null && !Objects.equals("", code)) {
                //去除前后空格
                code = StringUtil.trim(code);
                String sessionCode = (String) request.getSession().getAttribute("code");
                if (Objects.equals(code.toLowerCase(), sessionCode.toLowerCase())) {
                    outputStream.write("success".getBytes("utf-8"));
                    return;
                }
            }
            outputStream.write("fail".getBytes("utf-8"));
        }
    }
}
