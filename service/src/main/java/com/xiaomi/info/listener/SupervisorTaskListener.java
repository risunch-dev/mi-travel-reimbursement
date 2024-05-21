package com.xiaomi.info.listener;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * ClassName: SupervisorTaskListener
 * Package: com.xiaomi.info.listener
 * Description: 主管审批监听器
 *
 * @Author 朱安迪
 * @Create 2024/5/21 21:42
 * @Version 1.0
 */
@Slf4j
public class SupervisorTaskListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("收到事件通知: {}", delegateTask.getEventName());

        delegateTask.setAssignee("张三");
    }
}
