package com.base.domain;

import lombok.*;

import java.util.Date;

@Data
public class RoleResource {

    private Integer id;
    private String roleId;
    private String resourceId;
    private Boolean isDeleted;
    private Date createTime;

}
