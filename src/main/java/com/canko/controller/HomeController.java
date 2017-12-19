package com.canko.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by nrq on 2017/7/1.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @ResponseBody
    @RequestMapping("/stack/info")
    public String getStackInfo(){
        return JSON.toJSONString(Thread.getAllStackTraces());
    }

}
