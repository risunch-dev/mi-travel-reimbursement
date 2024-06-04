package com.xiaomi.info.model;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reimbursement {

    private Long id;
    private String item;

    private String name;

    private Integer amount;

    private String attachment;

    private Integer status;

    private String applyTime;
    private Date createTime;
    private String createUser;
    private Date updateTime;
    private String updateUser;


}
