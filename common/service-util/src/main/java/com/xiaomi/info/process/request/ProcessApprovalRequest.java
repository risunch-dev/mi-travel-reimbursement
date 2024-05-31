package com.xiaomi.info.process.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: ProcessApprovalRequest
 * Package: com.xiaomi.info.process.request
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/6/1 0:29
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessApprovalRequest {

    private Long processId;

    private String taskId;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 审批描述
     */
    private String description;
}
