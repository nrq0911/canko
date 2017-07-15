package com.canko.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by nrq on 2017/7/16.
 */
@Controller
public class BaseController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(value=ERROR_PATH)
    public String handleError(){
        return "error_page/error";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
