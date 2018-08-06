package com.imooc.homework.servlet;

import com.imooc.homework.data.User;
import com.imooc.homework.service.CourseDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "UserServlet", urlPatterns = {"/AddUser", "/SelectUser", "/DeleteUser"})
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Objects.equals("/AddUser", request.getServletPath())) {
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            String code = request.getParameter("checkCode");
            String operator = request.getParameter("operator");
            if (userName != null && password != null && code != null && operator != null) {
                String sessionCode = (String) request.getSession().getAttribute("code");
                if (Objects.equals(sessionCode.toLowerCase(), code.toLowerCase()) && Objects.equals(operator, "imooc")) {
                    if (!CourseDaoImpl.isUserExist(userName)) {
                        User user = new User(userName, password, "普通管理员");
                        CourseDaoImpl.addUser(user);
                        request.setAttribute("allUsers", CourseDaoImpl.getAllUsers());
                        request.getRequestDispatcher("/WEB-INF/views/biz/selectUsers.jsp").forward(request, response);
                    } else {
                        request.setAttribute("msg", "账号已经存在");
                        request.getRequestDispatcher("/WEB-INF/views/biz/addUser.jsp").forward(request, response);
                        return;
                    }
                } else {
                    request.setAttribute("msg", "验证码错误");
                    request.getRequestDispatcher("/WEB-INF/views/biz/addUser.jsp").forward(request, response);
                    return;
                }
            } else {
                request.setAttribute("msg", "账号/密码/验证码不能为空");
                request.getRequestDispatcher("/WEB-INF/views/biz/addUser.jsp").forward(request, response);
                return;
            }
        } else if (Objects.equals("/SelectUser", request.getServletPath())) {
            request.setAttribute("allUsers", CourseDaoImpl.getAllUsers());
            request.getRequestDispatcher("/WEB-INF/views/biz/selectUsers.jsp").forward(request, response);
        } else if (Objects.equals("/DeleteUser", request.getServletPath())) {
            String name = request.getParameter("username");
            if (!Objects.equals(name, null) && !Objects.equals(name, "")) {
                if (!Objects.equals(name, "imooc")) {
                    if (CourseDaoImpl.isUserExist(name)) {
                        CourseDaoImpl.delUser(name);
                        request.setAttribute("allUsers", CourseDaoImpl.getAllUsers());
                        request.getRequestDispatcher("/WEB-INF/views/biz/selectUsers.jsp").forward(request, response);
                    } else {
                        request.setAttribute("msg", "你要删除的用户不存在");
                        request.setAttribute("allUsers", CourseDaoImpl.getAllUsers());
                        request.getRequestDispatcher("/WEB-INF/views/biz/selectUsers.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("msg", "你不能删除超级管理员");
                    request.setAttribute("allUsers", CourseDaoImpl.getAllUsers());
                    request.getRequestDispatcher("/WEB-INF/views/biz/selectUsers.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("msg", "请选择你要删除的用户");
                request.setAttribute("allUsers", CourseDaoImpl.getAllUsers());
                request.getRequestDispatcher("/WEB-INF/views/biz/selectUsers.jsp").forward(request, response);
            }
        }
    }
}
