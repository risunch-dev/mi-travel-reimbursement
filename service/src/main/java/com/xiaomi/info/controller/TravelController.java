package com.xiaomi.info.controller;

import com.xiaomi.info.common.enums.ErrorCodes;
import com.xiaomi.info.exception.BasicRunException;
import com.xiaomi.info.model.TripApply;
import com.xiaomi.info.response.Response;
import com.xiaomi.info.service.TripApplyService;

import com.xiaomi.info.travel.request.TravelDeleteRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

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
@RequestMapping("/api/travel/apply")
public class TravelController {
    @Resource
    private TripApplyService tripApplyService;

    @PostMapping ("/create")
    public Response<Boolean> create(@RequestBody TripApply tripApply) {
        // 入参校验
        this.checkTripApplyCreateParameters(tripApply);
        Boolean result = tripApplyService.create(tripApply);
        return Boolean.TRUE.equals(result) ? Response.success(true) : Response.error("差旅申请创建失败");
    }


    @PostMapping("/update")
    public Response<Boolean> update(@RequestBody TripApply tripApply) {
        // 入参校验
        this.checkTripApplyUpdateParameters(tripApply);
        Boolean result = tripApplyService.update(tripApply);
        return Boolean.TRUE.equals(result) ? Response.success(true) : Response.error("差旅申请编辑失败");
    }

    @PostMapping("/delete")
    public Response<Boolean> delete(@RequestBody TravelDeleteRequest request) {
        // 入参校验
        checkTripApplyDeleteParameters(request);
        Boolean result = tripApplyService.delete(request.getId());
        return Boolean.TRUE.equals(result) ? Response.success(true) : Response.error("差旅申请删除失败");
    }

    @PostMapping("/detail")
    public Response<TripApply> detail(@RequestBody TravelDeleteRequest request) {
        // 入参校验
        checkTripApplyDeleteParameters(request);
        return Response.success(tripApplyService.detail(request.getId()));
    }

    /**
     * 差旅申请创建入参校验
     * @param tripApply
     */
    public void checkTripApplyCreateParameters(TripApply tripApply) {
        if (Objects.isNull(tripApply)) {
            throw new BasicRunException(ErrorCodes.MISSING_PARAMETER.getCode(), "请求参数不能为空");
        }
        if (StringUtils.isBlank(tripApply.getName())) {
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "差旅申请name不能为空");
        }
        if (StringUtils.isBlank(tripApply.getTravelCity())) {
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "差旅申请city不能为空");
        }
        if (Objects.isNull(tripApply.getDays()) || tripApply.getDays() <= NumberUtils.INTEGER_ZERO) {
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "差旅申请day不合法");
        }
    }

    /**
     * 差旅申请编辑入参校验
     * @param tripApply
     */
    public void checkTripApplyUpdateParameters(TripApply tripApply) {
        if (Objects.isNull(tripApply)) {
            throw new BasicRunException(ErrorCodes.MISSING_PARAMETER.getCode(), "请求参数不能为空");
        }
        if (Objects.isNull(tripApply.getId()) || tripApply.getId() <= NumberUtils.LONG_ZERO) {
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "差旅申请id不合法");
        }
    }

    /**
     * 差旅申请删除、详情入参校验
     * @param request
     */
    public void checkTripApplyDeleteParameters(TravelDeleteRequest request) {
        if (Objects.isNull(request)) {
            throw new BasicRunException(ErrorCodes.MISSING_PARAMETER.getCode(), "请求参数不能为空");
        }
        if (Objects.isNull(request.getId()) || request.getId() <= NumberUtils.LONG_ZERO) {
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "差旅申请id不合法");
        }
    }

}

