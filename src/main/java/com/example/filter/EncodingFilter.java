package com.example.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private String encoding = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String initEncoding = filterConfig.getInitParameter("encoding");
        if (initEncoding != null) {
            encoding = initEncoding;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        response.setContentType("text/html;charset=" + encoding);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}