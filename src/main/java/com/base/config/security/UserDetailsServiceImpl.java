package com.base.config.security;

import com.base.domain.Account;
import com.base.domain.Role;
import com.base.service.AccountService;
import com.base.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private AccountService accountService;
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //假设返回的用户信息如下;
        final UserDetailsEntity userDetailsEntity = new UserDetailsEntity();

        Account account = accountService.getAccountByUserName(username);
        Optional<Account> optionalAccount = Optional.ofNullable(account);

        if (!optionalAccount.isPresent()) {
            throw new UsernameNotFoundException("用户未找到!");
        }

        userDetailsEntity.setUsername(account.getUsername());
        userDetailsEntity.setPassword(account.getPassword());
        userDetailsEntity.setAccountNonExpired(account.getIsAccountNonExpired());
        userDetailsEntity.setAccountNonLocked(account.getIsAccountNonLocked());
        userDetailsEntity.setCredentialsNonExpired(account.getIsCredentialsNonExpired());
        userDetailsEntity.setEnabled(account.getIsEnabled());
        userDetailsEntity.setPermissions(getPermissions(account.getUsername()));

        return userDetailsEntity;

    }

    private Set<SimpleGrantedAuthority> getPermissions(String username) {
        List<Role> roles = roleService.getRoleListByUserName(username);
        final Set<SimpleGrantedAuthority> simpleGrantedAuthorities = new HashSet<>();
        roles.forEach((e) -> { //TODO lambda表达式优化
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(e.getRoleId()));
        });

        return simpleGrantedAuthorities;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
}
