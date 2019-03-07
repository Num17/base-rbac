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
public class Role {

    private Integer id;
    private String roleId;
    private String roleName;
    private Boolean isDeleted;
    private Date createTime;

}
