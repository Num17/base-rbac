package com.base.domain;

import lombok.*;

import java.util.Date;

@Data
public class Resource {

    private Integer id;
    private String resourceId;
    private String resourceName;
    private String url;
    private Boolean isDeleted;
    private Date createTime;

}