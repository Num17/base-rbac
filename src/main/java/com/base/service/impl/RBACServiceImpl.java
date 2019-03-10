package com.base.service.impl;

import com.base.manager.ResourceManager;
import com.base.service.RBACService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Component("rbacService")
public class RBACServiceImpl implements RBACService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    private ResourceManager resourceManager;

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) { //首先判断先当前用户是否是我们UserDetails对象。
            UserDetails userDetails = (UserDetails) principal;
            String userName = userDetails.getUsername();

            Set<String> urls = resourceManager.getUrlsByUsername(userName);
            for (String url : urls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    return Boolean.TRUE;
                }
            }
        }

        return Boolean.FALSE;
    }

    @Autowired
    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }
}
