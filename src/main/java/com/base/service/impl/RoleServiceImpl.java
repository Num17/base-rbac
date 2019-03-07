package com.base.service.impl;

import com.base.dao.RoleMapper;
import com.base.domain.Role;
import com.base.service.RoleService;
import com.base.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleMapper roleMapper;

    @Override
    public Set<String> getRoleIdsByUserName(String username) {
        return roleMapper.queryRoleIdsByUsername(username);
    }

    @Override
    public List<Role> getRoleListByUserName(String username) {

        if (StringUtil.isEmpty(username)) {
            return Collections.emptyList();
        }

        return roleMapper.queryRoleListByUserName(username);
    }

    @Autowired
    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }
}
