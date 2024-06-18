package com.xiaomi.info.service;

import com.xiaomi.info.travel.request.ProcessQueryListRequest;
import com.xiaomi.info.travel.response.ProcessQueryListResponse;
import com.xiaomi.info.travel.response.TaskResponse;
import com.xiaomi.info.travel.request.ProcessApproveRequest;
import org.activiti.engine.history.HistoricTaskInstance;

import java.util.List;

/**
 * ClassName: ITripRecordService
 * Package: com.xiaomi.info.service
 * Description:
 *
 * @Author 张芳泽
 * @Create 2024/6/4 22:30
 * @Version 1.0
 */
public interface TripRecordService {

    /**
     * 启动流程实例
     * @param id 差旅申请id
     * @return
     */
    Boolean submit(Long id);

    /**
     * 查看个人代办任务
     * @param id
     * @return
     */
    List<TaskResponse> query(Long id);

    /**
     * 审批通过
     * @param request
     * @return
     */
    Boolean approve(ProcessApproveRequest request);

    /**
     * 审批驳回
     * @param request
     * @return
     */
    Boolean reject(ProcessApproveRequest request);

    ProcessQueryListResponse findStarted(ProcessQueryListRequest request);

    /**
     * 查看已办理的流程
     * @param id
     * @return
     */
    List<HistoricTaskInstance> findCompleteTaskList(Long id);
}
