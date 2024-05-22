package activiti;


import com.xiaomi.info.StartApplication;
import com.xiaomi.info.common.enums.ErrorCodes;
import com.xiaomi.info.exception.BasicRunException;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: TravelProcessTest
 * Package: activiti
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/21 22:04
 * @Version 1.0
 */
@Slf4j
@SpringBootTest(classes = StartApplication.class)
public class TravelProcessTest {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    /**
     * 单个文件部署方式
     */
    @Test
    public void deployProcess() {
        // 流程部署
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("travel/travel.bpmn20.xml")
                .addClasspathResource("travel/travel.png")
                .name("差旅申请流程")
                .deploy();
        log.info("流程id：{},流程名称：{}", deploy.getId(), deploy.getName());
    }

    /**
     * 启动流程实例
     */
    @Test
    public void startUpProcess() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("travel");
        log.info("流程定义id = {},流程实例id = {},当前活动id = {}", processInstance.getProcessDefinitionId(),
                processInstance.getId(), processInstance.getActivityId());
    }

    /**
     * 启动流程实例，指定money
     */
    @Test
    public void startUpProcessAddBusinessKey() {
        Map<String, Object> map = new HashMap<>();
        map.put("money", "900");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("travel", map);
        log.info("业务表示={}", processInstance.getBusinessKey());
    }



    /**
     * 查询个人代办任务
     */
    @Test
    public void findTaskList() {
        String assign = "王五";
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee(assign)
                .list();
        for (Task task : list) {
            System.out.println("流程实例id：" + task.getProcessInstanceId());
            System.out.println("任务id：" + task.getId());
            System.out.println("任务负责人：" + task.getAssignee());
        }
    }

    /**
     * 处理当前任务
     */
    @Test
    public void completeTask() {
        Task task = taskService.createTaskQuery()
                .taskAssignee("王五")
                .singleResult();
        if(task == null) {
            log.error("当前经办人不存在待处理任务");
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "不存在待处理任务");
        }
        // 完成任务
        taskService.complete(task.getId());
    }

    /**
     * 查询已处理任务
     */
    @Test
    public void findCompleteTaskList() {
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee("王五")
                .finished()
                .list();
        for (HistoricTaskInstance historicTaskInstance : list) {
            log.info("流程实例id={}", historicTaskInstance.getProcessInstanceId());
            System.out.println("任务id：" + historicTaskInstance.getId());
            System.out.println("任务负责人：" + historicTaskInstance.getAssignee());
            System.out.println("任务名称：" + historicTaskInstance.getName());
        }
    }

    /**
     * 查询流程定义
     */
    @Test
    public void findProcessDefinitionList(){
        List<ProcessDefinition> definitionList = repositoryService.createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion()
                .desc()
                .list();
        // 输出流程定义信息
        for (ProcessDefinition processDefinition : definitionList) {
            System.out.println("流程定义id = " + processDefinition.getId());
            System.out.println("流程定义name = " + processDefinition.getName());
            System.out.println("流程定义key = " + processDefinition.getKey());
            System.out.println("流程定义version = " + processDefinition.getVersion());
            System.out.println("流程部署id = " + processDefinition.getDeploymentId());
        }
    }

    /**
     * 删除流程定义
     */
    @Test
    public void deleteDeployment() {
        // 部署id
        String deploymentId = "";
        // 如果该流程定义已有流程实例启动则删除时出错
        repositoryService.deleteDeployment(deploymentId);
        // 设置true 级联删除流程定义，即使该流程有流程实例启动也可以删除，设置为false非级别删除方式
        // repositoryService.deleteDeployment(deploymentId, true);
    }





}
