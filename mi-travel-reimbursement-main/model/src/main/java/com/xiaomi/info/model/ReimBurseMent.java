package com.xiaomi.info.model;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReimBurseMent {

    private Integer id;
    private String item;

    private String name;

    private Integer amount;

    private String attachMent;

    private Integer status;

    private String applyTime;
    private Date createTime;
    private String createUser;
    private Date updateTime;
    private String updateUser;


}
