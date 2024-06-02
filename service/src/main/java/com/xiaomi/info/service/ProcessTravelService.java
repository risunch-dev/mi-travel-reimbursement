package com.xiaomi.info.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaomi.info.model.process.XmProcess;

/**
 * ClassName: ProcessTravelService
 * Package: com.xiaomi.info.service
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/6/3 0:13
 * @Version 1.0
 */
public interface ProcessTravelService extends IService<XmProcess> {

    /**
     * 部署文件
     * @return
     */
    Boolean deployProcess();

    Boolean startUp(String money);

    Boolean findTaskList(String name);
}
