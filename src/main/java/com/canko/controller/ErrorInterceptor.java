package com.canko.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by nrq on 2017/7/15.
 */
@Controller
public class ErrorInterceptor implements HandlerInterceptor {

    private transient final Logger logger = Logger.getLogger(ErrorInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(response.getStatus() == 404){
            logger.info("用户访问的地址是:" + request.getRequestURI() + "\t IP是:" + getIpAddr(request));
            modelAndView.setViewName("error_page/404");
        }else if(response.getStatus() == 500){
            logger.info("用户访问的地址是:" + request.getRequestURI() + "\t IP是:" + getIpAddr(request));
            modelAndView.setViewName("error_page/500");
        }else if(response.getStatus() == 403){
            modelAndView.setViewName("error_page/403");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
