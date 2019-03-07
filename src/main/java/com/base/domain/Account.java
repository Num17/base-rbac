package com.base.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Account {

    private Integer id;
    private String username;
    private String password;
    private Boolean isAccountNonExpired;//账户是否过期,过期无法验证
    private Boolean isAccountNonLocked;//指定用户是否被锁定或者解锁,锁定的用户无法进行身份验证
    private Boolean isCredentialsNonExpired;//指示是否已过期的用户的凭据(密码),过期的凭据防止认证
    private Boolean isEnabled;//是否被禁用,禁用的用户不能身份验证
    private Boolean isDeleted;
    private Date createTime;

}
