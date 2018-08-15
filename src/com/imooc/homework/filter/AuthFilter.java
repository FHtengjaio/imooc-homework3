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
        boolean isAjaxRequest = request.getHeader("x-requested-with") != null &&
                Objects.equals(request.getHeader("x-requested-with"), "XMLHttpRequest");
        if (isAjaxRequest && (session == null || session.getAttribute("LoginUser") == null)) {
            response.setHeader("sessionstatus", "timeout");
        } else if(!isAjaxRequest && (session == null || session.getAttribute("LoginUser") == null)){
            request.setAttribute("msg", "session过期或者您还没有登录");
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
