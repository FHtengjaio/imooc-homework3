package com.imooc.homework.servlet;

import com.imooc.homework.service.UserDaoImpl;
import com.imooc.homework.utils.RegexUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Objects.equals("/Login", request.getServletPath())) {
            if (request.getSession().getAttribute("LoginUser") != null) {
                request.getRequestDispatcher("/WEB-INF/views/biz/server.jsp").forward(request, response);
            } else {
                String userName = request.getParameter("username");
                String password = request.getParameter("password");
                String code = request.getParameter("checkCode");
                if (userName != null && password != null && code != null && !Objects.equals("", userName)
                        && !Objects.equals("", password) && !Objects.equals("", code)) {
                    if (RegexUtil.isUserNameRight(userName) && RegexUtil.isPasswordRight(password)) {
                        if (UserDaoImpl.login(userName, password)) {
                            request.getSession().setAttribute("LoginUser", userName);
                            request.getRequestDispatcher("/WEB-INF/views/biz/server.jsp").forward(request, response);
                        } else {
                            request.setAttribute("msg", "账号密码错误");
                            request.getRequestDispatcher("/WEB-INF/views/biz/index.jsp").forward(request, response);
                        }
                    } else {
                        request.setAttribute("msg", "请设置格式正确的账号密码");
                        request.getRequestDispatcher("/WEB-INF/views/biz/index.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("msg", "请填写账号/密码/验证码");
                    request.getRequestDispatcher("/WEB-INF/views/biz/index.jsp").forward(request, response);
                }
            }
        }
    }
}
