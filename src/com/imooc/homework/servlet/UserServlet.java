package com.imooc.homework.servlet;

import com.imooc.homework.data.User;
import com.imooc.homework.service.UserDaoImpl;
import com.imooc.homework.utils.RegexUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "UserServlet", urlPatterns = {"/AddUser.do", "/SelectUser.do", "/DeleteUser.do"})
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Objects.equals("/AddUser.do", request.getServletPath())) {
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            String code = request.getParameter("checkCode");
            String operator = request.getParameter("operator");
            if (userName != null && password != null && code != null && operator != null
                    && !Objects.equals("",userName) && !Objects.equals("",password)
                    && !Objects.equals("",code) && !Objects.equals("",operator)) {
                if (RegexUtil.isUserNameRight(userName) && RegexUtil.isPasswordRight(password)) {
                    if (!UserDaoImpl.isUserExist(userName)) {
                        User user = new User(userName, password, "普通管理员");
                        UserDaoImpl.addUser(user);
                        response.sendRedirect(request.getContextPath() + "/SelectUser.do");
                    } else {
                        request.setAttribute("msg", "账号已经存在");
                        request.getRequestDispatcher("/WEB-INF/views/biz/addUser.jsp").forward(request, response);
                        return;
                    }
                } else {
                    request.setAttribute("msg", "请输入格式正确的账号密码");
                    request.getRequestDispatcher("/WEB-INF/views/biz/addUser.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("msg", "账号/密码/验证码不能为空");
                request.getRequestDispatcher("/WEB-INF/views/biz/addUser.jsp").forward(request, response);
                return;
            }
        } else if (Objects.equals("/SelectUser.do", request.getServletPath())) {
            request.setAttribute("allUsers", UserDaoImpl.getAllUsers());
            request.setAttribute("msg", request.getAttribute("msg"));
            request.getRequestDispatcher("/WEB-INF/views/biz/selectUsers.jsp").forward(request, response);
        } else if (Objects.equals("/DeleteUser.do", request.getServletPath())) {
            String name = request.getParameter("username");
            if (!Objects.equals(name, null) && !Objects.equals(name, "")) {
                if (!Objects.equals(name, "imooc")) {
                    if (UserDaoImpl.isUserExist(name)) {
                        UserDaoImpl.delUser(name);
                        request.getRequestDispatcher("/SelectUser.do").forward(request, response);
                    } else {
                        request.setAttribute("msg", "你要删除的用户不存在");
                        request.getRequestDispatcher("/SelectUser.do").forward(request, response);
                    }
                } else {
                    request.setAttribute("msg", "你不能删除超级管理员");
                    request.getRequestDispatcher("/SelectUser.do").forward(request, response);
                }
            } else {
                request.setAttribute("msg", "请选择你要删除的用户");
                request.getRequestDispatcher("/SelectUser.do").forward(request, response);
            }
        }
    }
}
