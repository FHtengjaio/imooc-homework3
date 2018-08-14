package com.imooc.homework.servlet;

import com.imooc.homework.data.User;
import com.imooc.homework.service.UserDaoImpl;
import com.imooc.homework.utils.RegexUtil;
import net.sf.json.JSONObject;

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
            String operator = request.getParameter("operator");
            System.out.println(userName);
            System.out.println(password);
            System.out.println(operator);
            JSONObject jsonObject = new JSONObject();
            if (userName != null && password != null && operator != null
                    && !Objects.equals("",userName) && !Objects.equals("",password)
                    && !Objects.equals("",operator)) {
                if (RegexUtil.isUserNameRight(userName) && RegexUtil.isPasswordRight(password)) {
                    if (!UserDaoImpl.isUserExist(userName)) {
                        User user = new User(userName, password, "普通管理员");
                        UserDaoImpl.addUser(user);
                        jsonObject.put("msg", null);
                        jsonObject.put("result", "success");
                        response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
                    } else {
                        jsonObject.put("msg", "账号已经存在");
                        jsonObject.put("result", "fail");
                        response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
                    }
                } else {
                    jsonObject.put("msg", "请输入格式正确的账号密码");
                    jsonObject.put("result", "fail");
                    response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
                }
            } else {
                jsonObject.put("msg", "账号/密码/验证码不能为空");
                jsonObject.put("result", "fail");
                response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
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
