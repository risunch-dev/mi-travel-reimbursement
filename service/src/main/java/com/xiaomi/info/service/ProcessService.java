package com.xiaomi.info.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaomi.info.model.process.XmProcess;
import com.xiaomi.info.process.response.ProcessResponse;
import com.xiaomi.info.process.request.ProcessQueryRequest;

/**
 * ClassName: ProcessService
 * Package: com.xiaomi.info.service
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/22 20:14
 * @Version 1.0
 */
public interface ProcessService extends IService<XmProcess> {
    IPage<ProcessResponse> selectPage(Page<ProcessResponse> pageParam, ProcessQueryRequest processQueryRequest);
}
