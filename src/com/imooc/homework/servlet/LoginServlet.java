package com.imooc.homework.servlet;

import com.imooc.homework.service.UserDaoImpl;

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
                if (!Objects.equals(userName, null) && !Objects.equals(password, null) && !Objects.equals(code, null)) {
                    boolean login = UserDaoImpl.login(userName, password);
                    String sessionCode = (String) request.getSession().getAttribute("code");
                    if (login && Objects.equals(sessionCode.toLowerCase(), code.toLowerCase())) {
                        request.getSession().setAttribute("LoginUser", userName);
                        request.getRequestDispatcher("/WEB-INF/views/biz/server.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("/WEB-INF/views/biz/index.jsp").forward(request, response);
                    }
                } else {
                    request.getRequestDispatcher("/WEB-INF/views/biz/index.jsp").forward(request, response);
                }
            }
        }
    }
}
