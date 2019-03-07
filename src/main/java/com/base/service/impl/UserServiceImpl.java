package com.base.service.impl;

import com.base.dao.UserMapper;
import com.base.domain.User;
import com.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    private UserMapper userMapper;


    @Override
    public User querByUserNameAndPassword(String username, String password) {
        return userMapper.querByUserNameAndPassword(username,password);
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
