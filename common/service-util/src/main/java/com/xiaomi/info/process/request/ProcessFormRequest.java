package com.xiaomi.info.process.request;

import lombok.Data;

/**
 * ClassName: ProcessFormRequest
 * Package: com.xiaomi.info.process.request
 * Description: 流程表单
 *
 * @Author 朱安迪
 * @Create 2024/5/23 10:42
 * @Version 1.0
 */
@Data
public class ProcessFormRequest {
        /**
         * 审批模板id
         */
        private Long processTemplateId;

        /**
         * 审批类型id
         */
        private Long processTypeId;

        /**
         * 表单值
         */
        private String formValues;
}
