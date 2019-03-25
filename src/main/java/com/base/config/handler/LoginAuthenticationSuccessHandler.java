package com.base.config.handler;

import com.base.bean.BaseResponse;
import com.base.config.jwt.JWTUtil;
import com.base.constant.AppConstant;
import com.base.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录成功
 */

@Slf4j
@Component
public class LoginAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        String username = authentication.getName();
        response.addHeader(SecurityConstant.HEADER, JWTUtil.builderToken(username));
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding(AppConstant.ENCODE);
        response.setContentType(AppConstant.JSON_CONTENT_TYPE);

        log.info("用户[{]]登录成功!", username);

        BaseResponse successResponse = BaseResponse.SUCCESS_RESPONSE;
        String success = JsonUtil.toJson(successResponse);
        PrintWriter out = response.getWriter();
        out.write(success);
        out.close();
        out.flush();
    }


}
