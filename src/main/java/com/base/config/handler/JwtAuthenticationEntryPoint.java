package com.base.config.handler;

import com.base.bean.BaseResponse;
import com.base.constant.AppConstant;
import com.base.util.JsonUtil;
import com.google.gson.Gson;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用户未登录返回信息
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setCharacterEncoding(AppConstant.ENCODE);
        response.setContentType(AppConstant.JSON_CONTENT_TYPE);

        BaseResponse successResponse = BaseResponse.newErrorResponse("请登录系统以后操作!");
        String error = JsonUtil.toJson(successResponse);

        PrintWriter out = response.getWriter();
        out.write(error);
        out.close();
        out.flush();
    }
}
