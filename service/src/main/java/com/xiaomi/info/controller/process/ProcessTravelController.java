package com.xiaomi.info.controller.process;

import com.xiaomi.info.response.Response;
import com.xiaomi.info.service.ProcessTravelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * ClassName: ProcessTravelController
 * Package: com.xiaomi.info.controller.process
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/6/3 0:09
 * @Version 1.0
 */
@RestController
@RequestMapping("/travel/process")
public class ProcessTravelController {

    @Resource
    private ProcessTravelService processTravelService;


    /**
     * 部署文件
     * @return
     */
    @GetMapping("/deploy")
    public Response<Boolean> deployProcess() {
        return processTravelService.deployProcess() ?
                Response.success(Boolean.TRUE) : Response.error("部署文件失败");
    }

    /**
     * 启动流程实例，传入money
     * @return
     */
    @PostMapping("/startUp")
    public Response<Boolean> startUp(String money) {
        processTravelService.startUp(money);
        return Response.success(Boolean.TRUE);
    }

    /**
     * 查看个人代办任务
     * @param name 用户名称
     * @return
     */
    @PostMapping("/findTaskList")
    public Response<Boolean> findTaskList(String name) {
        processTravelService.findTaskList(name);
        return Response.success();
    }


}
