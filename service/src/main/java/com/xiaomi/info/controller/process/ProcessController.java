package com.xiaomi.info.controller.process;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaomi.info.process.response.ProcessResponse;
import com.xiaomi.info.process.request.ProcessQueryRequest;
import com.xiaomi.info.response.Response;
import com.xiaomi.info.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: ProcessController
 * Package: com.xiaomi.info.controller.process
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/22 20:19
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/api/process")
@SuppressWarnings({"unchecked", "rawtypes"})
public class ProcessController {

    @Autowired
    private ProcessService processService;

    @GetMapping("/{page}/{limit}")
    public Response index(@PathVariable Long page,
                          @PathVariable Long limit,
                          ProcessQueryRequest processQueryRequest) {
        Page<ProcessResponse> pageParam = new Page<>(page, limit);
        IPage<ProcessResponse> pageModel = processService.selectPage(pageParam, processQueryRequest);
        return Response.success(pageModel);
    }

}
