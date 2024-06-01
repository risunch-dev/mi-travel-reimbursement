package com.xiaomi.info.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaomi.info.model.process.XmProcess;
import com.xiaomi.info.process.request.ProcessApprovalRequest;
import com.xiaomi.info.process.request.ProcessFormRequest;
import com.xiaomi.info.process.response.ProcessResponse;
import com.xiaomi.info.process.request.ProcessQueryRequest;

import java.util.Map;

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

    /**
     * 部署流程定义
     * @param deployPath
     */
    void deployByZip(String deployPath);

    /**
     * 启动流程实例
     * @param processFormRequest
     * @param userId
     */
    void startUp(ProcessFormRequest processFormRequest, Long userId);

    /**
     * 查询待处理任务列表
     * @param pageParam
     * @return
     */
    IPage<ProcessResponse> findPending(Page<XmProcess> pageParam, Long userId);


    /**
     * 审批详情
     * @param id
     * @return
     */
    Map<String, Object> showDetail(Long id);


    /**
     * 审批
     * @param request
     */
    void approve(ProcessApprovalRequest request);

    /**
     * 查看已处理审批
     * @param pageParam
     * @return
     */
    IPage<ProcessResponse> findProcessed(Page<XmProcess> pageParam, Long userId);
}
