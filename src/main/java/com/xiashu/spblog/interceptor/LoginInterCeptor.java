package com.xiashu.spblog.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 拦截器
public class LoginInterCeptor extends HandlerInterceptorAdapter {

    // 预处理
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("user")==null){
            response.sendRedirect("/admin");
        }

        return true;
    }
}
