package com.xiaomi.info.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaomi.info.model.process.XmProcessTemplate;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * ClassName: ProcessTemplateService
 * Package: com.xiaomi.info.service
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/22 15:38
 * @Version 1.0
 */
public interface ProcessTemplateService extends IService<XmProcessTemplate> {

    IPage<XmProcessTemplate> selectPage(Page<XmProcessTemplate> pageParam);

    /**
     * 部署流程定义（发布）
     * @param id
     */
    void publish(Long id);
}
