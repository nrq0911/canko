package com.canko.controller;

import com.alibaba.fastjson.JSON;
import com.canko.common.HttpUtils;
import com.canko.common.MD5;
import com.canko.domain.AdminUser;
import com.canko.service.AdminUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by nrq on 2017/7/1.
 */
@Controller
public class HomeController {

    public static Map<String, String> userIpMap = new ConcurrentHashMap<>();

    private static final Logger log = Logger.getLogger(HomeController.class);

    @Autowired
    private AdminUserService userService;

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @ResponseBody
    @RequestMapping("/stack/info")
    public String getStackInfo(){
        return JSON.toJSONString(Thread.getAllStackTraces());
    }

    @ResponseBody
    @RequestMapping("/login.do")
    public Map<String, Object> login(HttpServletRequest request, String username, String password){
        Map<String, Object> result = new HashMap<>();
        result.put("code", 400);
        if(StringUtils.isAnyBlank(username, password)){
            result.put("msg","账号和密码不能为空！");
            return result;
        }
        try{
            AdminUser user = userService.getUserByUsername(username);
            if(Objects.isNull(user) || !MD5.md5(password).equals(user.getPassword())){
                result.put("msg","账号密码错误,请重新输入！");
                return result;
            }
            String ip = HttpUtils.getIpAddr(request);
            userIpMap.put(username, ip);
            result.put("code", 200);
            result.put("msg","success");
            return result;
        }catch (Exception e){
            result.put("code", 500);
            result.put("msg","Server error!");
            log.info("user{username:"+username +",password:"+ password +"} login error due to " + e);
            return result;
        }
    }

    @ResponseBody
    @RequestMapping("/logout.do")
    public Map<String, Object> logout(HttpServletRequest request, String username){
        Map<String, Object> result = new HashMap<>();
        try {
            String ip = HttpUtils.getIpAddr(request);
            if (ip.equals(userIpMap.get(username))) {
                userIpMap.remove(username);
            }
            result.put("code", 200);
            result.put("msg","success");
            return result;
        }catch (Exception e){
            result.put("code", 500);
            result.put("msg","Server error!");
            log.info("user{username:"+username +"} logout error due to " + e);
            return result;
        }
    }

}
