package com.base.service;

import com.base.domain.User;

public interface UserService {

    User querByUserNameAndPassword(String username, String password);

}
