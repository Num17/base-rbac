package com.base.Manager;

import com.base.service.ResourceService;
import com.base.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ResourceManager {

    private RoleService roleService;
    private ResourceService resourceService;

    public Set<String> getUrlsByUsername(String username) {
        Set<String> urls = resourceService.getUrlsByUsername(username);
        Set<String> roleIds = roleService.getRoleIdsByUserName(username);
        Set<String> urlsFromRole = resourceService.getUrlsByRoleIds(roleIds);
        urls.addAll(urlsFromRole);
        return urls;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }
}
