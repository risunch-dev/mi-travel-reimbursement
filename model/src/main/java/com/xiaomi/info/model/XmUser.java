package com.xiaomi.info.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ClassName: XmUser
 * Package: com.xiaomi.info.model
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/17 14:17
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class XmUser {
    private Long id;
    private String name;
    private String email;
    private Integer status;
    private Integer leaderId;
    private Date createTime;
    private String createUser;
    private Date updateTime;
    private String updateUser;
    private Long departmentId;
    private String departmentName;
}
