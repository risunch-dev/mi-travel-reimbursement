package com.xiaomi.info.controller.process;

import com.xiaomi.info.model.process.XmProcessTemplate;
import com.xiaomi.info.model.process.XmProcessType;
import com.xiaomi.info.process.response.ProcessTypeListResponse;
import com.xiaomi.info.response.Response;
import com.xiaomi.info.service.ProcessTemplateService;
import com.xiaomi.info.service.ProcessTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName: ProcessApiController
 * Package: com.xiaomi.info.controller.process
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/23 10:06
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/process")
public class ProcessApiController {

    @Autowired
    private ProcessTypeService processTypeService;

    @Autowired
    private ProcessTemplateService processTemplateService;

    /**
     "获取全部审批分类及模板
     *
     */
    @GetMapping("/findProcessType")
    public Response<ProcessTypeListResponse> findProcessType() {
        List<XmProcessType> list = processTypeService.findProcessType();
        ProcessTypeListResponse response = ProcessTypeListResponse.builder()
                .xmProcessTypeList(list)
                .build();
        return Response.success(response);
    }

    /**
     * 获取审批模板
     * @param processTemplateId
     * @return
     */
    @GetMapping("getProcessTemplate/{processTemplateId}")
    public Response<XmProcessTemplate> get(@PathVariable Long processTemplateId) {
        XmProcessTemplate xmProcessTemplate = processTemplateService.getById(processTemplateId);
        return Response.success(xmProcessTemplate);
    }
}
