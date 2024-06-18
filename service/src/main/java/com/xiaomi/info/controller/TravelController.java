package com.xiaomi.info.controller;

import com.xiaomi.info.common.enums.ErrorCodes;
import com.xiaomi.info.exception.BasicRunException;
import com.xiaomi.info.model.TripApply;
import com.xiaomi.info.response.Response;
import com.xiaomi.info.service.TripApplyService;

import com.xiaomi.info.travel.request.TravelDeleteRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * ClassName: TravelController
 * Package: com.xiaomi.info.controller
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/18 13:43
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/travel/apply/process")
public class TravelController {
    @Resource
    private TripApplyService tripApplyService;

    @PostMapping ("/create")
    public Response<Boolean> create(@RequestBody TripApply tripApply) {
        tripApplyService.create(tripApply);
        return Response.success(true);
    }


    @PostMapping("/update")
    public Response<Boolean> update(@RequestBody TripApply tripApply) {
        if(tripApply == null) {
            log.error("当前id对应的差旅申请为空,id={}", tripApply.getId());
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "当前id不存在");
        }
        tripApplyService.update(tripApply);
        return Response.success();
    }

    @PostMapping("/delete")
    public Response<Boolean> delete(@RequestBody TravelDeleteRequest request) {

        tripApplyService.delete(request.getId());
        return Response.success(true);
    }

    @PostMapping("/detail")
    public Response<TripApply> detail(@RequestBody TravelDeleteRequest request) {

        return Response.success(tripApplyService.detail(request.getId()));
    }
}

