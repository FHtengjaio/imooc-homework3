package com.imooc.homework.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.do", "/Home"})
public class AuthFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);

        //是否为ajax请求
        boolean isAjaxRequest = request.getHeader("x-requested-with") != null &&
                Objects.equals(request.getHeader("x-requested-with"), "XMLHttpRequest");

        //如果是ajax请求就添加头信息，让ajax中complete中判断是否session过期,或者没有登录
        if (isAjaxRequest && (session == null || session.getAttribute("LoginUser") == null)) {
            response.setHeader("sessionstatus", "timeout");
        } else if(!isAjaxRequest && (session == null || session.getAttribute("LoginUser") == null)){
            //如果是浏览器请求，session过期或者没有登录，就直接跳转至登录页面
            request.setAttribute("msg", "session过期或者您还没有登录");
            request.getRequestDispatcher("index").forward(request, response);
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
