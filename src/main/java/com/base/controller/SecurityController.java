package com.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

    //登录页面
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    //登录失败跳转页面,可直接配置登录失败handler
    @RequestMapping("/login-failed")
    public String loginFail() {
        return "security/login-fail";
    }

    //登录成功跳转页面,可直接配置登录成功handler
    @RequestMapping("/login-success")
    public String loginSucess() {
        return "index";
    }

}

