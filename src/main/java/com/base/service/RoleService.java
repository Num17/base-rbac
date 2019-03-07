package com.base.service;

import com.base.domain.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {


    List<Role> getRoleListByUserName(String username);

    Set<String> getRoleIdsByUserName(String username);
}
