package com.canko.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginController {

    private transient final Logger logger = Logger.getLogger(LoginController.class);

    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public String loginAdmin(){
        return "login/admin";
    }

    @RequestMapping(value = "/admin",method = RequestMethod.POST)
    public String loginAdmin(String username, String password){
        logger.info("User {username=" + username + ", password=" + password + "} login admin.");
        return "redirect:/admin/goods/goodsList";
    }


}
