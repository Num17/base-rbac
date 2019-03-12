package com.base.config.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * 自定义过滤器(放入自定义资源管理，授权验证，投标器方便),FilterSecurityInterceptor为springSecurity默认过滤器
 */
public class UrlFilterSecurityInterceptor extends FilterSecurityInterceptor {

    public UrlFilterSecurityInterceptor(FilterInvocationSecurityMetadataSource securityMetadataSource,
                                        AccessDecisionManager accessDecisionManager, AuthenticationManager authenticationManager) {
        this.setSecurityMetadataSource(securityMetadataSource);
        this.setAccessDecisionManager(accessDecisionManager);
        this.setAuthenticationManager(authenticationManager);

    }

}
