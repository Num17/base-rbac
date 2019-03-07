package com.base.service;

import java.util.Set;

public interface ResourceService {

    Set<String> getUrlsByRoleIds(Set<String> roleIds);

    Set<String> getUrlsByUsername(String username);
}
