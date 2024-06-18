package com.xiaomi.info.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaomi.info.common.enums.ErrorCodes;
import com.xiaomi.info.entity.TripRecord;
import com.xiaomi.info.exception.BasicRunException;
import com.xiaomi.info.mapper.TripApplyMapper;
import com.xiaomi.info.mapper.TripRecordMapper;
import com.xiaomi.info.mapper.XmUserMapper;
import com.xiaomi.info.model.TripApply;
import com.xiaomi.info.model.XmUser;
import com.xiaomi.info.service.TripRecordService;
import com.xiaomi.info.travel.response.TaskResponse;
import com.xiaomi.info.travel.request.ProcessApproveRequest;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * ClassName: TripRecordServiceImpl
 * Package: com.xiaomi.info.service.impl
 * Description:
 *
 * @Author 张芳泽
 * @Create 2024/6/4 22:32
 * @Version 1.0
 */
@Slf4j
@Service
public class TripRecordServiceImpl implements TripRecordService {

    @Resource
    private TripRecordMapper tripRecordMapper;

    @Resource
    private TripApplyMapper tripApplyMapper;

    @Resource
    private XmUserMapper xmUserMapper;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    /**
     * 启动流程实例
     * @param id 差旅申请id
     * @return
     */
    @Override
    public Boolean submit(Long id) {
        TripApply tripApply = tripApplyMapper.selectById(id);
        if(tripApply == null) {
            log.error("没有对应的差旅申请,id={}", id);
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "没有id对应的差旅申请");
        }
        TripRecord tripRecord = new TripRecord();
        String name = tripApply.getName();
        XmUser user = xmUserMapper.selectOne(new LambdaQueryWrapper<XmUser>()
                .eq(XmUser::getName, name));
        tripRecord.setUserId(user.getId());
        tripRecord.setPlanId(user.getLeaderId());
        Integer amount = tripApply.getAmount();
        tripRecord.setType(1);
        tripRecord.setReason("null");
        tripRecord.setStatus(2);
        Date date = new Date();
        tripRecord.setUpdateTime(date);
        tripRecord.setCreateTime(date);
        Integer row = tripRecordMapper.insert(tripRecord);
        System.out.println(row);
        Map<String,Object> variables = new HashMap<>();
        variables.put("amount",amount);
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("test1",variables);
        //
        Task task = taskService.createTaskQuery()
                .processInstanceId(pi.getProcessInstanceId())
                .singleResult();
        //提交任务的时候传入流程变量
        System.out.println("instanceID:"+task.getProcessInstanceId()+",taskID:"+task.getId()+",name:"+task.getName()+",assignee:"+task.getAssignee());
        taskService.complete(task.getId());
        task = taskService.createTaskQuery()
                .processInstanceId(pi.getProcessInstanceId())
                .singleResult();

        System.out.println("instanceID:"+task.getProcessInstanceId()+",taskID:"+task.getId()+",name:"+task.getName()+",assignee:"+task.getAssignee());
        //如果任务对象为空,则流程执行结束
        System.out.println("----------------------------");
        return true;
    }

    /**
     * 查看个人代办任务
     * @param id userId
     * @return
     */
    @Override
    public List<TaskResponse> query(Long id) {
        XmUser xmUser = xmUserMapper.selectOne(new LambdaQueryWrapper<XmUser>()
                .eq(XmUser::getId, id));
        String name = xmUser.getName();
        String assignee = name;
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee(assignee)//只查询该任务负责人的任务
                .list();

        List<TaskResponse> list1 = new ArrayList<>();
        if (list == null)
            System.out.println("该用户没有待办请求");
        else {
            for (Task task : list) {
                TaskResponse tv = new TaskResponse();
                tv.setInstanceID(task.getProcessInstanceId());
                tv.setName(task.getAssignee());
                tv.setTaskId(task.getId());
                tv.setTaskName(task.getName());
                System.out.println("instanceID:"+task.getProcessInstanceId()+",taskID:"+task.getId()+",name:"+task.getName()+",assignee:"+task.getAssignee());
                list1.add(tv);
            }
        }
        return list1;
    }

    /**
     * 审批通过
     * @param request
     * @return
     */
    @Override
    public Boolean approve(ProcessApproveRequest request) {
        return examine(request.getId(), request.getInstanceId(), request.getDesc(), "approve");

    }

    /**
     * 审批驳回
     * @param request
     * @return
     */
    @Override
    public Boolean reject(ProcessApproveRequest request) {
        return examine(request.getId(), request.getInstanceId(), request.getDesc(), "reject");

    }

    private Boolean examine(Long id, String instanceID, String desc,String command) {
        XmUser xmUser = xmUserMapper.selectOne(new LambdaQueryWrapper<XmUser>()
                .eq(XmUser::getId, id));
        String assignee = xmUser.getName();
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee(assignee)// 只查询该任务负责人的任务
                .list();
        if (list == null) {
            log.error("该用户没有代办任务,userId={}", id);
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "该用户没有代办任务");
        } else {
            for (Task task : list) {
                if (instanceID.equals(task.getProcessInstanceId())) {
                    Map<String,Object> variables = new HashMap<>();
                    variables.put("description", desc);
                    variables.put("outcome",command);
                    taskService.complete(task.getId(),variables);
                    return true;
                }
            }
        }
        return true;
    }
}
