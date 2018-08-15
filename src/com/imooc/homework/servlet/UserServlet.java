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
            JSONObject jsonObject = new JSONObject();
            String headerContent = request.getHeader("x-requested-with");

            //判断请求是否为ajax请求，添加用户采用ajax请求，所以只响应ajax请求
            if (headerContent != null && Objects.equals(headerContent, "XMLHttpRequest")) {

                //判断提交的表单数据是否为null或者为空
                if (userName != null && password != null
                        && !Objects.equals("", userName)
                        && !Objects.equals("", password)) {

                    //正则校验
                    if (RegexUtil.isUserNameRight(userName) && RegexUtil.isPasswordRight(password)) {

                        //判断用户是否已经存在
                        if (!UserDaoImpl.isUserExist(userName)) {
                            //用户不存在就添加，并向前端返回成功的数据
                            User user = new User(userName, password, "普通管理员");
                            UserDaoImpl.addUser(user);
                            jsonObject.put("msg", null);
                            jsonObject.put("result", "success");
                            response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
                        } else {
                            //用户存在就添加，向前端返回添加失败的数据
                            jsonObject.put("msg", "账号已经存在");
                            jsonObject.put("result", "fail");
                            response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
                        }
                    } else {
                        //正则验证失败，向前端返回账号密码格式错误的数据
                        jsonObject.put("msg", "请输入格式正确的账号密码");
                        jsonObject.put("result", "fail");
                        response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
                    }
                } else {
                    //账号密码为空，向前端返回错误数据
                    jsonObject.put("msg", "账号/密码/验证码不能为空");
                    jsonObject.put("result", "fail");
                    response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
                }
            } else {
                //如果是浏览器的请求，这直接转发主页
                response.sendRedirect("/Home");
            }
        } else if (Objects.equals("/SelectUser.do", request.getServletPath())) {
            request.setAttribute("allUsers", UserDaoImpl.getAllUsers());
            request.setAttribute("msg", request.getAttribute("msg"));
            request.getRequestDispatcher("/WEB-INF/views/biz/selectUsers.jsp").forward(request, response);
        } else if (Objects.equals("/DeleteUser.do", request.getServletPath())) {
            String name = request.getParameter("username");
            // 判断参数是否为空或者null
            if (!Objects.equals(name, null) && !Objects.equals(name, "")) {
                //防止用户篡改页面，将imooc删除
                if (!Objects.equals(name, "imooc")) {
                    //用户篡改页面数据，判断用户是否存在
                    if (UserDaoImpl.isUserExist(name)) {
                        //存在就删除用户，页面转发到用户查询页面
                        UserDaoImpl.delUser(name);
                        request.getRequestDispatcher("/SelectUser.do").forward(request, response);
                    } else {
                        //用户不存就提示
                        request.setAttribute("msg", "你要删除的用户不存在");
                        request.getRequestDispatcher("/SelectUser.do").forward(request, response);
                    }
                } else {
                    //提示不能删除超级管理员
                    request.setAttribute("msg", "你不能删除超级管理员");
                    request.getRequestDispatcher("/SelectUser.do").forward(request, response);
                }
            } else {
                //提示选择需要删除的用户
                request.setAttribute("msg", "请选择你要删除的用户");
                request.getRequestDispatcher("/SelectUser.do").forward(request, response);
            }
        }
    }
}
