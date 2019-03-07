package com.base.controller;

import com.base.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

    private AccountService accountService;

    @RequestMapping("/register")
    public String register(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        accountService.register(username, password);

        return "list";
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
