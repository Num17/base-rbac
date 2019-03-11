package com.base.config.security;

import com.base.bean.BaseResponse;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录成功
 */
@Component
public class LoginAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static Logger logger = LoggerFactory.getLogger(LoginAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setCharacterEncoding(SecurityConstant.ENCODE);
        response.setContentType(SecurityConstant.JSON_CONTENT_TYPE);

        //TODO 后续优化在JsonUtil内
        Gson gson = new Gson();
        BaseResponse successResponse = BaseResponse.SUCCESS_RESPONSE;
        String success = gson.toJson(successResponse);

        PrintWriter out = response.getWriter();
        out.write(success);
        out.close();

    }

}
