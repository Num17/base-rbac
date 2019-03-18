package com.base.config.jwt;

import com.base.config.handler.SecurityConstant;
import com.base.util.StringUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(SecurityConstant.HEADER);
        if (StringUtil.isEmpty(header) || !header.startsWith(SecurityConstant.TOKEN_SUFFIX)) {
//            chain.doFilter(request, response);
//            return;
//            throw new BadCredentialsException("请登录!");
        }

//        if (!JWTUtil.checkToken(header)) {
            //TODO 提示登录
//            throw new BadCredentialsException("凭证已失效!请重新登录!");
//        }

        chain.doFilter(request, response);
    }

}
