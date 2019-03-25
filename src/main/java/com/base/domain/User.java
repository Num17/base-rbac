package com.base.domain;

import lombok.*;

import java.util.Date;

@Data
public class User {

    private Integer id;
    private String account;
    private String username;
    private String password;
    private String email;
    private Boolean isDeleted;
    private Date createTime;

}