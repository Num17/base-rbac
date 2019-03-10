package com.base.service;

import com.base.domain.RoleResource;

import java.util.List;
import java.util.Set;

public interface RoleResourceService {

    List<RoleResource> getRoleResourceByResourceIds(Set<String> resourceIds);

}
