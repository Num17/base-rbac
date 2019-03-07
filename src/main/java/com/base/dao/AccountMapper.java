package com.base.dao;

import com.base.domain.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {


    Account queryByUserName(String username);

    int addAccount(Account account);

}
