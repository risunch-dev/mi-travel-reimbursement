package com.xiaomi.info.model.process;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaomi.info.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * ClassName: XmProcessRecord
 * Package: com.xiaomi.info.model.process
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/22 13:49
 * @Version 1.0
 */
@Data
@Builder
@ApiModel(description = "XmProcessRecord")
@TableName("xm_process_record")
public class XmProcessRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "审批流程id")
    @TableField("process_id")
    private Long processId;

    @ApiModelProperty(value = "审批描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "操作用户id")
    @TableField("operate_user_id")
    private Long operateUserId;

    @ApiModelProperty(value = "操作用户")
    @TableField("operate_user")
    private String operateUser;

}
