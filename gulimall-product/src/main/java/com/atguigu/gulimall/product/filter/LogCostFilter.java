package com.atguigu.gulimall.product.filter;

import com.atguigu.gulimall.product.config.FilterConfig;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Caler_赵康乐
 * @create 2020-11-29 19:14
 * @description :
 */
public class LogCostFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("Execute cost="+(System.currentTimeMillis()-start));
    }
    @Override
    public void destroy() {

    }
}
