package com.xiaomi.info.travel.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: TravelDetailResponse
 * Package: com.xiaomi.info.travel.response
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/6/18 15:00
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravelDetailResponse implements Serializable {

    private Long id;
    private String name;
    private Integer status;
    private String travelCity;
    private String attachment;
    private Integer days;
    private Integer amount;
    private String createTime;
    private String createUser;
    private String updateTime;
    private String updateUser;
}
