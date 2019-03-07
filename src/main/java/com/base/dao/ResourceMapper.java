package com.base.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

@Mapper
public interface ResourceMapper {


    Set<String> queryUrlsByRoleIds(@Param("roleIds") Set<String> roleIds);


    Set<String> queryUrlsByUsername(String username);
}
