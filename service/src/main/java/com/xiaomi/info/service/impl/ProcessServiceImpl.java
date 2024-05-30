package com.xiaomi.info.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomi.info.mapper.XmProcessMapper;
import com.xiaomi.info.model.XmUser;
import com.xiaomi.info.model.process.XmProcess;
import com.xiaomi.info.model.process.XmProcessRecord;
import com.xiaomi.info.model.process.XmProcessTemplate;
import com.xiaomi.info.process.request.ProcessFormRequest;
import com.xiaomi.info.process.response.ProcessResponse;
import com.xiaomi.info.process.request.ProcessQueryRequest;
import com.xiaomi.info.service.ProcessRecordService;
import com.xiaomi.info.service.ProcessService;
import com.xiaomi.info.service.ProcessTemplateService;
import com.xiaomi.info.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

/**
 * ClassName: ProcessServiceImpl
 * Package: com.xiaomi.info.service.impl
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/22 20:16
 * @Version 1.0
 */
@Slf4j
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class ProcessServiceImpl extends ServiceImpl<XmProcessMapper, XmProcess> implements ProcessService {

    @Autowired
    private XmProcessMapper xmProcessMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ProcessTemplateService processTemplateService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProcessRecordService processRecordService;

    @Override
    public IPage<ProcessResponse> selectPage(Page<ProcessResponse> pageParam, ProcessQueryRequest processQueryRequest) {
        IPage<ProcessResponse> page = xmProcessMapper.selectPage(pageParam, processQueryRequest, null);
        return page;
    }

    /**
     * 部署流程定义
     * @param deployPath
     */
    @Override
    public void deployByZip(String deployPath) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(deployPath);
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        // 部署Zip
        Deployment deployment = repositoryService.createDeployment().addZipInputStream(zipInputStream).deploy();
        log.info("部署Id={},部署Name={}", deployment.getId(), deployment.getName());

    }

    /**
     * 启动流程实例
     * @param processFormRequest
     */
    @Override
    public void startUp(ProcessFormRequest processFormRequest, Long userId) {
        XmUser xmUser = userService.getById(userId);

        XmProcessTemplate processTemplate = processTemplateService.getById(processFormRequest.getProcessTemplateId());

        String workNo = System.currentTimeMillis() + "";
        XmProcess xmProcess = XmProcess.builder()
                .processTemplateId(processFormRequest.getProcessTemplateId())
                .processTypeId(processFormRequest.getProcessTypeId())
                .formValues(processFormRequest.getFormValues())
                .processCode(workNo)
                .userId(userId)
                .title(xmUser.getName() + "发起" + processTemplate.getName() + "申请")
                .status(1)
                .build();

        xmProcessMapper.insert(xmProcess);

        // 绑定业务id
        String businessKey = String.valueOf(xmProcess.getId());
        // 流程参数
        Map<String, Object> variables = new HashMap<>();
        // 将表单数据放入流程实例中
        JSONObject jsonObject = JSON.parseObject(xmProcess.getFormValues());
        JSONObject formData = jsonObject.getJSONObject("formData");
        Map<String, Object> map = new HashMap<>();
        // 循环转换
        for (Map.Entry<String, Object> entry : formData.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        variables.put("data", map);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processTemplate.getProcessDefinitionKey(), businessKey, variables);
        // 业务表关联当前流程实例id
        String processInstanceId = processInstance.getId();
        xmProcess.setProcessInstanceId(processInstanceId);

        // 查询下一个审批人，可能有多个（并行审批）
        List<Task> taskList = this.getCurrentTaskList(processInstanceId);
        if (!CollectionUtils.isEmpty(taskList)) {
            List<String> assigneeList = new ArrayList<>();
            for(Task task : taskList) {
                XmUser user = userService.getByUserName(task.getAssignee());
                assigneeList.add(user.getName());

                // 推送消息给下一个审批人
            }
            xmProcess.setProcessInstanceId(processInstance.getId());
            xmProcess.setDescription("等待" + StringUtils.join(assigneeList.toArray(), ",") + "审批");
        }
        xmProcessMapper.updateById(xmProcess);

        // 记录操作审批信息记录
        processRecordService.record(xmProcess.getId(), 1, "发起申请", userId);
    }

    /**
     * 查询待处理任务列表
     * @param pageParam
     * @return
     */
    @Override
    public IPage<ProcessResponse> findPending(Page<XmProcess> pageParam, Long userId) {
        // 根据当前人的ID查询
        TaskQuery query = taskService.createTaskQuery()
                .taskAssignee(userService.getById(userId).getName())
                .orderByTaskCreateTime().desc();
        List<Task> list = query.listPage((int) ((pageParam.getCurrent() - 1) * pageParam.getSize()), (int) pageParam.getSize());
        long totalCount = query.count();

        List<ProcessResponse> processList = new ArrayList<>();
        // 根据流程的业务ID查询实体并关联
        for (Task item : list) {
            String processInstanceId = item.getProcessInstanceId();
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
            if (processInstance == null) {
                continue;
            }
            // 业务key
            String businessKey = processInstance.getBusinessKey();
            if (businessKey == null) {
                continue;
            }
            XmProcess xmProcess = this.getById(Long.parseLong(businessKey));
            ProcessResponse processVo = new ProcessResponse();
            BeanUtils.copyProperties(xmProcess, processVo);
            processVo.setTaskId(item.getId());
            processList.add(processVo);
        }
        IPage<ProcessResponse> page = new Page<>(pageParam.getCurrent(), pageParam.getSize(), totalCount);
        page.setRecords(processList);
        return page;
    }

    /**
     * 查看审批详情
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> showDetail(Long id) {

        XmProcess xmProcess = this.getById(id);

        List<XmProcessRecord> processRecordList = processRecordService.list(new LambdaQueryWrapper<XmProcessRecord>()
                .eq(XmProcessRecord::getProcessId, id));
        XmProcessTemplate processTemplate = processTemplateService.getById(xmProcess.getProcessTemplateId());

        Map<String, Object> map = new HashMap<>();
        map.put("process", xmProcess);
        map.put("processRecordList", processRecordList);
        map.put("processTemplate", processTemplate);

        // 计算当前用户是否可以审批
        // 能够查看详情的用户不是都能审批，审批后也不能重复审批
        boolean isApprove = false;
        List<Task> taskList = this.getCurrentTaskList(xmProcess.getProcessInstanceId());
        if (!CollectionUtils.isEmpty(taskList)) {
            for(Task task : taskList) {
                XmUser xmUser = userService.getById(id);
                if(task.getAssignee().equals(xmUser.getName())) {
                    isApprove = true;
                }
            }
        }
        map.put("isApprove", isApprove);
        return map;

    }

    /**
     * 获取当前任务列表
     * @param processInstanceId
     * @return
     */
    private List<Task> getCurrentTaskList(String processInstanceId) {
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        return tasks;
    }

}
