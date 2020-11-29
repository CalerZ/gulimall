package com.atguigu.gulimall.product.filter;

import com.atguigu.gulimall.product.config.FilterConfig;
import com.atguigu.gulimall.product.utils.FormHashMap;
import com.atguigu.gulimall.product.utils.Utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Caler_赵康乐
 * @create 2020-11-29 19:14
 * @description :
 */
public class RepeatFormFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        filterChain.doFilter(request,response);

    }
    @Override
    public void destroy() {

    }
}
