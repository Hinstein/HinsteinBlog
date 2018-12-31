package com.hinstein.blog.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.handler.Handler;

/**
 * @BelongsProject: spring-boot-web
 * @BelongsPackage: com.hinstein.blog.component
 * @Author: Hinstein
 * @CreateTime: 2018-11-18 13:36
 * @Description:
 */

/**
 * 登录请求，拦截器
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    //目标方法执行前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if (user == null) {
            //未登录，返回登录页面
            request.setAttribute("msg", "没有权限,请先登录");
            request.getRequestDispatcher("/").forward(request, response);
            return false;
        } else {
            //已登录，放行请求
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
