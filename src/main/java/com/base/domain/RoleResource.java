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
public class RoleResource {

    private Integer id;
    private String roleId;
    private String resourceId;
    private Boolean isDeleted;
    private Date createTime;

}
