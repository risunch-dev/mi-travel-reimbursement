package com.xiaomi.info.travel.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: ProcessApproveRequest
 * Package: com.xiaomi.info.travel.request
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/6/18 11:11
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessApproveRequest implements Serializable {
    /**
     * 用户id
     */
    private Long id;

    /**
     * 流程实例id
     */
    private String instanceId;

    /**
     * 审批描述
     */
    private String desc;
}
