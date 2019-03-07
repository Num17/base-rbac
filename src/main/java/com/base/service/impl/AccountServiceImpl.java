package com.base.service.impl;

import com.base.dao.AccountMapper;
import com.base.domain.Account;
import com.base.service.AccountService;
import com.base.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountMapper accountMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public int register(String username, String password) {
        String encodePassword = passwordEncoder.encode(password);

        Account account = new Account();
        account.setUsername(username);
        account.setPassword(encodePassword);
        account.setCreateTime(DateUtil.now());
        account.setIsAccountNonExpired(Boolean.TRUE);
        account.setIsAccountNonLocked(Boolean.TRUE);
        account.setIsCredentialsNonExpired(Boolean.TRUE);
        account.setIsDeleted(Boolean.FALSE);
        account.setIsEnabled(Boolean.TRUE);
        return accountMapper.addAccount(account);
    }

    @Override
    public Account getAccountByUserName(String username) {
        return accountMapper.queryByUserName(username);
    }

    @Autowired
    public void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

}
