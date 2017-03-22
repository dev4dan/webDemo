package com.dev4dan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
/**
 * Created by danielwood on 2017/2/3.
 */
@Controller
@RequestMapping("/home")
public class DemoController {

    @RequestMapping("/info")
    @ResponseBody
    public ModelAndView info() {

        String message = "this info method";
        return new ModelAndView("info", "message", message);

    }
}
