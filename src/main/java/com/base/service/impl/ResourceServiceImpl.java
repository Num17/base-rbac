package com.base.service.impl;

import com.base.dao.ResourceMapper;
import com.base.domain.Resource;
import com.base.service.ResourceService;
import com.base.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class ResourceServiceImpl implements ResourceService {

    private ResourceMapper resourceMapper;

    @Override
    public Set<String> getUrlsByRoleIds(Set<String> roleIds) {

        Set<String> urls = Collections.emptySet();
        if (!CollectionUtil.isEmpty(roleIds)) {
            urls.addAll(resourceMapper.queryUrlsByRoleIds(roleIds));
        }

        return urls;
    }

    @Override
    public List<Resource> getAllResource() {
        return resourceMapper.queryAllResource();
    }

    @Override
    public Set<String> getUrlsByUsername(String username) {
        return resourceMapper.queryUrlsByUsername(username);
    }

    @Autowired
    public void setResourceMapper(ResourceMapper resourceMapper) {
        this.resourceMapper = resourceMapper;
    }
}
