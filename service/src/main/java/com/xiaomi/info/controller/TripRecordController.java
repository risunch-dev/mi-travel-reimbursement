package com.xiaomi.info.controller;

import com.xiaomi.info.response.Response;
import com.xiaomi.info.service.TripRecordService;
import com.xiaomi.info.travel.response.ProcessQueryListResponse;
import com.xiaomi.info.travel.request.ProcessApproveRequest;
import com.xiaomi.info.travel.request.ProcessQueryListRequest;
import com.xiaomi.info.travel.request.TravelDeleteRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * ClassName: TripRecordController
 * Package: com.xiaomi.info.controller
 * Description: 审批流接口
 *
 * @Author 张芳泽
 * @Create 2024/6/4 22:27
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/travel/apply")
public class TripRecordController {

    @Resource
    private TripRecordService tripRecordService;

    /**
     * 启动流程实例
     * @param request 差旅申请id
     * @return
     */
    @PostMapping("/submit")
    public Response<Boolean> submit(@RequestBody TravelDeleteRequest request) {
        return Response.success(tripRecordService.submit(request.getId()));
    }

    /**
     * 查看个人代办任务
     * @param  request userId
     * @return
     */
    @PostMapping ("/queryList")
    public Response<ProcessQueryListResponse> queryList(@RequestBody ProcessQueryListRequest request) {
        ProcessQueryListResponse response = ProcessQueryListResponse.builder()
                .list(tripRecordService.query(request.getId()))
                .build();
        return Response.success(response);
    }

    /**
     * 审批通过
     * @param request userId、instanceId、desc
     * @return
     */
    @PostMapping("/approve")
    public Response<Boolean> approve(@RequestBody ProcessApproveRequest request) {
        return Response.success(tripRecordService.approve(request));
    }

    /**
     * 审批驳回
     * @param request userId、instanceId、desc
     * @return
     */
    @PostMapping("/reject")
    public Response<Boolean> reject(@RequestBody ProcessApproveRequest request) {
        return Response.success(tripRecordService.reject(request));
    }
}
