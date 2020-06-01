package com.sxs.filter;

import com.mysql.cj.Session;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class LogginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;

        // 防止重复过滤
        /**
         * 第一次请求一个页面会重定向到登入页面，这个时候还是没有登入，所以又到这个过滤器中来
         * 如果在请求一个 非登入页面 非登入相关的servlet 并且没有登入标识 时登入标识不存在，就重定向到登入界面
         * 防止请求js或者css也重定向到登入界面
         */
        if (!req.getRequestURI().endsWith("css") && !req.getRequestURI().endsWith("js") && !req.getRequestURI().equals("/ManageSystem/login.jsp") && !req.getRequestURI().equals("/ManageSystem/login") && null == req.getSession().getAttribute("logging")) {
            resp.sendRedirect("/ManageSystem/login.jsp");
            return;
        }

        filterChain.doFilter(req, resp);
    }
}
