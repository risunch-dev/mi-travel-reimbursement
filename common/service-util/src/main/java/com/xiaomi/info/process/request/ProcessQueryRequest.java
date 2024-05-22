package com.xiaomi.info.process.request;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * ClassName: ProcessQueryRequest
 * Package: com.xiaomi.info.process.request
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/22 20:06
 * @Version 1.0
 */
@Data
public class ProcessQueryRequest {

    private String keyword;

    private Long userId;

    @TableField("process_template_id")
    private Long processTemplateId;

    private Long processTypeId;

    private String createTimeBegin;

    private String createTimeEnd;

    private Integer status;
}
