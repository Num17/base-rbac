package com.base.dao;

import com.base.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {


    User querByUserNameAndPassword(String username, String password);


}