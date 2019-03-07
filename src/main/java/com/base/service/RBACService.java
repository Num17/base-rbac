package com.base.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface RBACService {

    boolean hasPermission(HttpServletRequest request, Authentication authentication);

}
