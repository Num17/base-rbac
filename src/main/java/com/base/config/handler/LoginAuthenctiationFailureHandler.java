package com.base.config.handler;

import com.base.bean.BaseResponse;
import com.base.constant.AppConstant;
import com.base.util.JsonUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录失败处理
 */
@Component
public class LoginAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private static Logger logger = LoggerFactory.getLogger(LoginAuthenctiationFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setCharacterEncoding(AppConstant.ENCODE);
        response.setContentType(AppConstant.JSON_CONTENT_TYPE);


        BaseResponse successResponse = BaseResponse.ERROR_RESPONSE;
        String error = JsonUtil.toJson(successResponse);

        PrintWriter out = response.getWriter();
        out.write(error);
        out.close();
        out.flush();

    }

}
