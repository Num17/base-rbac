package com.base.config.security;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Setter
@NoArgsConstructor
public class UserDetailsEntity implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private Set<SimpleGrantedAuthority> permissions;// 权限集合
    private boolean isAccountNonExpired;//账户是否过期,过期无法验证
    private boolean isAccountNonLocked;//指定用户是否被锁定或者解锁,锁定的用户无法进行身份验证
    private boolean isCredentialsNonExpired;//指示是否已过期的用户的凭据(密码),过期的凭据防止认证
    private boolean isEnabled;//是否被禁用,禁用的用户不能身份验证

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }


}
