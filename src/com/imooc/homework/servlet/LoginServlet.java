package com.imooc.homework.servlet;

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

@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Objects.equals("/Login", request.getServletPath())) {
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            String headerContent = request.getHeader("x-requested-with");
            if (headerContent != null && Objects.equals(headerContent, "XMLHttpRequest")) {
                JSONObject jsonObject = new JSONObject();
                if (userName != null && password != null && !Objects.equals("", userName)
                        && !Objects.equals("", password)) {
                    if (RegexUtil.isUserNameRight(userName) && RegexUtil.isPasswordRight(password)) {
                        if (UserDaoImpl.login(userName, password)) {
                            request.getSession().setAttribute("LoginUser", userName);
                            jsonObject.put("msg", null);
                            jsonObject.put("result", "success");
                            response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
                        } else {
                            jsonObject.put("msg", "账号密码错误");
                            jsonObject.put("result", "fail");
                            response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
                        }
                    } else {
                        jsonObject.put("msg", "请设置格式正确的账号密码");
                        jsonObject.put("result", "fail");
                        response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
                    }
                } else {
                    jsonObject.put("msg", "请填写账号/密码/验证码");
                    jsonObject.put("result", "fail");
                    response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
                }
            } else {
                response.sendRedirect("/index");
            }
        }
    }
}
