package com.base.controller;

import com.base.bean.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/test")
public class TestController {

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/redis")
    public BaseResponse testUrl(HttpSession session) {
        SecurityContext context = SecurityContextHolder.getContext();


        System.out.println(session.getId());

        return BaseResponse.newSuccessResponse("访问redis url成功");
    }

    @RequestMapping(value = "mysql")
    public BaseResponse testMysql() {
        return BaseResponse.newSuccessResponse("访问mysql url success");
    }

}
