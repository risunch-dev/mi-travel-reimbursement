package com.xiaomi.info.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaomi.info.model.process.XmProcessType;

import java.util.List;

/**
 * ClassName: ProcessTypeService
 * Package: com.xiaomi.info.service
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/22 13:55
 * @Version 1.0
 */
public interface ProcessTypeService extends IService<XmProcessType> {
    /**
     * 查询所有审批分类及其模板
     * @return
     */
    List<XmProcessType> findProcessType();
}
