package com.base.dao;

import com.base.domain.RoleResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface RoleResourceMapper {

    List<RoleResource> queryRoleResourceByResourceIds(Set<String> resourceIds);

}
