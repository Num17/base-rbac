package com.base.dao;

import com.base.domain.Role;

import java.util.List;
import java.util.Set;

public interface RoleMapper {


    List<Role> queryRoleListByUserName(String username);


    Set<String> queryRoleIdsByUsername(String username);
}
