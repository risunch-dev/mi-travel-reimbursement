package activiti;


import com.xiaomi.info.StartApplication;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}
