package com.sxs.filter;

import com.sxs.wrapper.EncodeHttpServletRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class EncodeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //chain.doFilter(req, resp);
        // 转换类型
        HttpServletRequest req = (HttpServletRequest)servletRequest;

        // 设置响应编码
        servletResponse.setContentType("text/html; charset=utf-8");

        // 设置每一个servlet的编码

        // 基于post的编码
        if (req.getMethod().equalsIgnoreCase("post")) {
            req.setCharacterEncoding("utf-8");
        }else if (req.getMethod().equalsIgnoreCase("get")) {
            req = new EncodeHttpServletRequest(req);
        }
        filterChain.doFilter(req, servletResponse);

        // 设置响应的编码
    }

    @Override
    public void destroy() {

    }
}
