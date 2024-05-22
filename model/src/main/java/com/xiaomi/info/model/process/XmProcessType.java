package com.xiaomi.info.model.process;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaomi.info.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * ClassName: XmProcessType
 * Package: com.xiaomi.info.model.process
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/22 13:38
 * @Version 1.0
 */
@Data
@ApiModel(description = "XmProcessType")
@TableName("xm_process_type")
public class XmProcessType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "类型名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "描述")
    @TableField("description")
    private String description;

    @TableField(exist = false)
    private List<XmProcessTemplate> processTemplateList;
}
