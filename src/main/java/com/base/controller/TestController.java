package com.base.controller;

import com.base.bean.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/redis")
    public BaseResponse testUrl() {
        return BaseResponse.SUCCESS_RESPONSE;
    }

}
