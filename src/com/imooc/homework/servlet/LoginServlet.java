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

            //判断请求是否为ajax请求，登录采用ajax请求，所以只响应ajax请求
            if (headerContent != null && Objects.equals(headerContent, "XMLHttpRequest")) {
                JSONObject jsonObject = new JSONObject();

                //判断表单数据是否为空或者为null
                if (userName != null && password != null && !Objects.equals("", userName)
                        && !Objects.equals("", password)) {

                    //正则校验是否通过
                    if (RegexUtil.isUserNameRight(userName) && RegexUtil.isPasswordRight(password)) {

                        //判断账号密码是否正确
                        if (UserDaoImpl.login(userName, password)) {
                            request.getSession().setAttribute("LoginUser", userName);   //登录成功session中添加用户名
                            //向前端返回数据
                            jsonObject.put("msg", null);
                            jsonObject.put("result", "success");
                            response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
                        } else {
                            //向前端返回账号密码的数据
                            jsonObject.put("msg", "账号密码错误");
                            jsonObject.put("result", "fail");
                            response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
                        }
                    } else {
                        //向前端返回账号密码格式错误的数据
                        jsonObject.put("msg", "请设置格式正确的账号密码");
                        jsonObject.put("result", "fail");
                        response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
                    }
                } else {
                    //向前端返回账号密码为空的数据
                    jsonObject.put("msg", "请填写账号/密码");
                    jsonObject.put("result", "fail");
                    response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
                }
            } else {
                //如果是浏览器请求直接返回登录页面
                response.sendRedirect("/index");
            }
        }
    }
}
