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
public class Resource {

    private Integer id;
    private String resourceId;
    private String resourceName;
    private String url;
    private Boolean isDeleted;
    private Date createTime;

}