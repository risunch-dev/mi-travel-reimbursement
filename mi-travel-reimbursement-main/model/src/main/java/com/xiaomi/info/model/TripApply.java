package com.xiaomi.info.model;

import lombok.*;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripApply{
    private Long id;
    private String name;
    private Integer status;
    private String travelCity;
    private String attachMent;
    private Integer days;
    private Integer amount;
    private Date createTime;
    private String createUser;
    private Date updateTime;
    private String updateUser;


}
