package com.base.service;

import com.base.domain.Resource;

import java.util.List;
import java.util.Set;

public interface ResourceService {

    Set<String> getUrlsByRoleIds(Set<String> roleIds);

    Set<String> getUrlsByUsername(String username);

    List<Resource> getAllResource();
}
