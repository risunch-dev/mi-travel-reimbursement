package com.xiaomi.info.process.response;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * ClassName: ProcessRequest
 * Package: com.xiaomi.info.process.request
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/22 20:09
 * @Version 1.0
 */
@Data
public class ProcessResponse {

    private Long id;

    private Date createTime;

    private String processCode;

    private Long userId;

    private String name;

    @TableField("process_template_id")
    private Long processTemplateId;

    private String processTemplateName;

    private Long processTypeId;

    private String processTypeName;

    private String title;

    private String description;

    private String formProps;

    private String formOptions;

    private String formValues;

    private String processInstanceId;

    private String currentAuditor;

    private Integer status;

    private String taskId;

}
