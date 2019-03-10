package com.base.dao;

import com.base.domain.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface ResourceMapper {


    Set<String> queryUrlsByRoleIds(@Param("roleIds") Set<String> roleIds);


    Set<String> queryUrlsByUsername(String username);

    List<Resource> queryAllResource();

}
