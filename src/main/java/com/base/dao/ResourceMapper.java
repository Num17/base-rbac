package com.base.dao;

import com.base.domain.Resource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResourceMapper {

    List<Resource> queryAllResource();

}
