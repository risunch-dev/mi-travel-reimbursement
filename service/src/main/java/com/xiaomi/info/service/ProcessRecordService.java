package com.xiaomi.info.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaomi.info.model.process.XmProcessRecord;

/**
 * ClassName: ProcessRecordService
 * Package: com.xiaomi.info.service
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/23 13:54
 * @Version 1.0
 */
public interface ProcessRecordService extends IService<XmProcessRecord> {
    void recordProcess(Long processId, Integer status, String description, Long userId);
}
