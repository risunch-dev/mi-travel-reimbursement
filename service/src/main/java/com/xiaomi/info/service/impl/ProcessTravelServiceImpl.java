package com.xiaomi.info.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomi.info.mapper.XmProcessMapper;
import com.xiaomi.info.model.process.XmProcess;
import com.xiaomi.info.service.ProcessTravelService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: ProcessTravelServiceImpl
 * Package: com.xiaomi.info.service.impl
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/6/3 0:14
 * @Version 1.0
 */
@Service
@Slf4j
public class ProcessTravelServiceImpl extends ServiceImpl<XmProcessMapper, XmProcess> implements ProcessTravelService {

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;


    /**
     * 部署文件
     * @return
     */
    @Override
    public Boolean deployProcess() {
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("travel/travel.bpmn20.xml")
                .addClasspathResource("travel/travel.png")
                .name("差旅申请流程")
                .deploy();
        log.info("流程id：{},流程名称：{}", deploy.getId(), deploy.getName());
        return Boolean.TRUE;
    }

    @Override
    public Boolean startUp(String money) {
        Map<String, Object> map = new HashMap<>();
        map.put("money", money);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("travel", map);
        log.info("业务标识={}", processInstance.getBusinessKey());
        return true;
    }

    /**
     * 查询个人代办任务
     * @param
     * @return
     */
    @Override
    public Boolean findTaskList(String name) {
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee(name)
                .list();
        for(Task task : list) {
            System.out.println("流程实例id：" + task.getProcessInstanceId());
            System.out.println("任务id：" + task.getId());
            System.out.println("任务负责人：" + task.getAssignee());
        }
        return true;
    }

}
