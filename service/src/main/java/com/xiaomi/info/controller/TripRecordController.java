package com.xiaomi.info.controller;

import com.xiaomi.info.common.enums.ErrorCodes;
import com.xiaomi.info.exception.BasicRunException;
import com.xiaomi.info.response.Response;
import com.xiaomi.info.service.TripRecordService;
import com.xiaomi.info.travel.response.ProcessQueryListResponse;
import com.xiaomi.info.travel.request.ProcessApproveRequest;
import com.xiaomi.info.travel.request.ProcessQueryListRequest;
import com.xiaomi.info.travel.request.TravelDeleteRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

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
@RequestMapping("/api/travel/apply/process")
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
        // 入参校验
        checkTripRecordSubmitParameters(request);
        Boolean result = tripRecordService.submit(request.getId());
        return Boolean.TRUE.equals(result) ? Response.success(true) : Response.error("启动流程实例失败");
    }

    /**
     * 查看个人代办任务
     * @param  request userId
     * @return
     */
    @PostMapping ("/queryList")
    public Response<ProcessQueryListResponse> queryList(@RequestBody ProcessQueryListRequest request) {
        // 入参校验
        checkTripRecordQueryListParameters(request);
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
        // 入参校验
        checkTripRecordApproveParameters(request);
        Boolean result = tripRecordService.approve(request);
        return Boolean.TRUE.equals(result) ? Response.success(true) : Response.error("审批通过失败");
    }

    /**
     * 审批驳回
     * @param request userId、instanceId、desc
     * @return
     */
    @PostMapping("/reject")
    public Response<Boolean> reject(@RequestBody ProcessApproveRequest request) {
        // 入参校验
        checkTripRecordApproveParameters(request);
        Boolean result = tripRecordService.reject(request);
        return Boolean.TRUE.equals(result) ? Response.success(true) : Response.error("审批驳回失败");
    }

    /**
     * 启动流程实例参数校验
     * @param request
     */
    public void checkTripRecordSubmitParameters(TravelDeleteRequest request) {
        if (Objects.isNull(request)) {
            throw new BasicRunException(ErrorCodes.MISSING_PARAMETER.getCode(), "请求参数不能为空");
        }
        if (Objects.isNull(request.getId()) || request.getId() <= NumberUtils.LONG_ZERO) {
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "差旅申请id不合法");
        }
    }

    /**
     * 查看个人代办任务参数校验
     * @param request
     */
    public void checkTripRecordQueryListParameters(ProcessQueryListRequest request) {
        if (Objects.isNull(request)) {
            throw new BasicRunException(ErrorCodes.MISSING_PARAMETER.getCode(), "请求参数不能为空");
        }
        if (Objects.isNull(request.getId()) || request.getId() <= NumberUtils.LONG_ZERO) {
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "查看待办任务的userId不合法");
        }
    }

    /**
     * 审批通过/驳回参数校验
     * @param request
     */
    public void checkTripRecordApproveParameters(ProcessApproveRequest request) {
        if (Objects.isNull(request)) {
            throw new BasicRunException(ErrorCodes.MISSING_PARAMETER.getCode(), "请求参数不能为空");
        }
        if (Objects.isNull(request.getId()) || request.getId() <= NumberUtils.LONG_ZERO) {
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "审批的userId不合法");
        }
        if (StringUtils.isBlank(request.getInstanceId())) {
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "流程实例id不能为空");
        }
        if (StringUtils.isBlank(request.getDesc())) {
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "审批描述不能为空");
        }
    }

}
