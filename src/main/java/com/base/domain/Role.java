package com.base.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Role {

    private Integer id;
    private String roleId;
    private String roleName;
    private Boolean isDeleted;
    private Date createTime;

}
