package com.xiaomi.info.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripApply implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    private String name;
    private Integer status;
    private String travelCity;
    private String attachment;
    private Integer days;
    private Integer amount;
    private Date createTime;
    private String createUser;
    private Date updateTime;
    private String updateUser;


}
