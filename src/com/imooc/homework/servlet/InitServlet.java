package com.imooc.homework.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "InitServlet",
        urlPatterns = {"/TopInit", "/LeftInit", "/LoginInit", "/AddUserInit", "/AddCourseInit", "/ImportCourseInit"})
public class InitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Objects.equals(request.getServletPath(), "/TopInit")) {
            request.getRequestDispatcher("/WEB-INF/views/biz/top.jsp").forward(request, response);
        } else if (Objects.equals(request.getServletPath(), "/LeftInit")) {
            request.getRequestDispatcher("/WEB-INF/views/biz/left.jsp").forward(request, response);
        } else if (Objects.equals("/LoginInit", request.getServletPath())) {
            request.getSession().removeAttribute("LoginUser");
            request.getRequestDispatcher("/WEB-INF/views/biz/index.jsp").forward(request, response);
        } else if (Objects.equals("/AddUserInit", request.getServletPath())) {
            request.getRequestDispatcher("/WEB-INF/views/biz/addUser.jsp").forward(request, response);
        } else if (Objects.equals("/AddCourseInit", request.getServletPath())) {
            request.getRequestDispatcher("/WEB-INF/views/biz/addCourse.jsp").forward(request, response);
        } else if (Objects.equals("/ImportCourseInit", request.getServletPath())) {
            request.getRequestDispatcher("/WEB-INF/views/biz/courseImport.jsp").forward(request, response);
        }
    }
}
