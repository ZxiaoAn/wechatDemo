package com.offcial.core;

import javax.servlet.*;
import java.io.IOException;

/**
 * servlet拦截器
 *
 * @author Zza
 * @date 2017-10-19 9:23
 */
public class MyServletFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }

}
