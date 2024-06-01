package com.xiaomi.info.controller.process;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaomi.info.model.process.XmProcess;
import com.xiaomi.info.model.process.XmProcessTemplate;
import com.xiaomi.info.model.process.XmProcessType;
import com.xiaomi.info.process.request.ProcessApprovalRequest;
import com.xiaomi.info.process.request.ProcessFormRequest;
import com.xiaomi.info.process.response.ProcessDetailResponse;
import com.xiaomi.info.process.response.ProcessResponse;
import com.xiaomi.info.process.response.ProcessTypeListResponse;
import com.xiaomi.info.response.Response;
import com.xiaomi.info.service.ProcessService;
import com.xiaomi.info.service.ProcessTemplateService;
import com.xiaomi.info.service.ProcessTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private ProcessService processService;

    /**
     * 启动流程
     * @param processFormRequest
     * @return
     */
    @PostMapping("/startUp")
    public Response<Boolean> start(@RequestBody ProcessFormRequest processFormRequest, @RequestBody Long userId) {
        processService.startUp(processFormRequest, userId);
        return Response.success();
    }

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

    /**
     * 待处理查询
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/findPending/{page}/{limit}")
    public Response findPending(@PathVariable Long page,
                                @PathVariable Long limit,
                                @PathVariable Long userId) {
        Page<XmProcess> pageParam = new Page<>(page, limit);
        return Response.success(processService.findPending(pageParam, userId));
    }

    /**
     * 获取审批详情
     * @param id
     * @return
     */
    @GetMapping("showDetail/{id}")
    public Response<ProcessDetailResponse> showDetail(@PathVariable Long id) {
        ProcessDetailResponse response = ProcessDetailResponse.builder()
                .map(processService.showDetail(id))
                .build();
        return Response.success(response);
    }

    /**
     * 审批
     * @param request
     * @return
     */
    @PostMapping("approve")
    public Response approve(@RequestBody ProcessApprovalRequest request) {
        processService.approve(request);
        return Response.success();
    }

    /**
     * 查看已处理审批
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/findProcessed/{page}/{limit}")
    public Response findProcessed(@PathVariable Long page,
                                  @PathVariable Long limit,
                                  @PathVariable Long userId) {
        Page<XmProcess> pageParam = new Page<>(page, limit);
        return Response.success(processService.findProcessed(pageParam, userId));
    }

    /**
     * 已发起
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/findStarted/{page}/{limit}")
    public Response findStarted(@PathVariable Long page,
                                @PathVariable Long limit,
                                @PathVariable Long userId) {
        Page<ProcessResponse> pageParam = new Page<>(page, limit);
        return Response.success(processService.findStarted(pageParam, userId));
    }


}
