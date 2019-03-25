package com.base.config.handler;


import com.base.bean.BaseResponse;
import com.base.constant.AppConstant;
import com.base.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 权限不足处理
 */
@Slf4j
@Component
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse resp, AccessDeniedException e) throws IOException {
        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        resp.setCharacterEncoding(AppConstant.ENCODE);
        resp.setContentType(AppConstant.JSON_CONTENT_TYPE);


        BaseResponse errorResponse = BaseResponse.newErrorResponse("权限不足！请联系管理员");
        String error = JsonUtil.toJson(errorResponse);

        PrintWriter out = resp.getWriter();
        out.write(error);
        out.close();
        out.flush();

    }

}
