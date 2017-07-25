package com.canko.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by nrq on 2017/7/16.
 */
@Controller
public class ErrorHandlerController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(){
        return "error_page/500";
    }

    @RequestMapping("/error/400")
    public String handle400Error(){
        return "error_page/400";
    }

    @RequestMapping("/error/403")
    public String handle403Error(){
        return "error_page/403";
    }

    @RequestMapping("/error/404")
    public String handle404Error(){
        return "error_page/404";
    }

    @RequestMapping("/error/500")
    public String handle500Error(){
        return "error_page/500";
    }

    @Override
    public String getErrorPath() {
        return "error";
    }
}
