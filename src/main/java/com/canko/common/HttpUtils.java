package com.canko.common;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by nrq on 2017/6/25.
 */
public class HttpUtils {

    public static void main(String...arg){
        String[] strings = {"123","123","123",""};
        System.out.print(org.apache.commons.lang3.StringUtils.isNoneBlank(strings));
    }

    public static String getIpAddr(HttpServletRequest request) {
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
