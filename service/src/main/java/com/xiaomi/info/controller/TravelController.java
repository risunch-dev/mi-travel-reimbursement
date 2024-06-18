package com.xiaomi.info.controller;

import com.xiaomi.info.common.entity.PageResult;
import com.xiaomi.info.common.enums.ErrorCodes;
import com.xiaomi.info.common.response.PageResponse;
import com.xiaomi.info.common.utils.DateUtils;
import com.xiaomi.info.exception.BasicRunException;
import com.xiaomi.info.model.TripApply;
import com.xiaomi.info.response.Response;
import com.xiaomi.info.service.TripApplyService;

import com.xiaomi.info.travel.request.TravelDeleteRequest;
import com.xiaomi.info.travel.request.TravelListRequest;
import com.xiaomi.info.travel.response.TravelCreateResponse;
import com.xiaomi.info.travel.response.TravelDetailResponse;
import com.xiaomi.info.travel.response.TravelListResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
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

    private static final String COMMON = "请求参数不能为空";
    @Resource
    private TripApplyService tripApplyService;

    /**
     * 列表，分页查询
     * @param request
     * @return
     */
    @PostMapping("/list")
    public Response<TravelListResponse> list(@RequestBody TravelListRequest request) {
        // 校验参数
        checkUserListParameters(request);
        String createUser = request.getCreateUser();
        PageResult<TripApply> pageResult = tripApplyService.list(createUser, request.getPageNum(), request.getPageSize());
        TravelListResponse response = new TravelListResponse();
        List<TravelDetailResponse> list = new ArrayList<>();
        for(TripApply tripApply : pageResult.getData()) {
            TravelDetailResponse travelDetailResponse = TravelDetailResponse.builder()
                    .id(tripApply.getId())
                    .name(tripApply.getName())
                    .status(tripApply.getStatus())
                    .travelCity(tripApply.getTravelCity())
                    .attachment(tripApply.getAttachment())
                    .days(tripApply.getDays())
                    .amount(tripApply.getAmount())
                    .createTime(DateUtils.getDefaultDateString(tripApply.getCreateTime()))
                    .createUser(tripApply.getCreateUser())
                    .updateTime(DateUtils.getDefaultDateString(tripApply.getUpdateTime()))
                    .updateUser(tripApply.getUpdateUser())
                    .build();
            list.add(travelDetailResponse);
        }
        response.setList(list);
        PageResponse pageResponse = new PageResponse();
        BeanUtils.copyProperties(pageResult.getPageInfo(), pageResponse);
        response.setPageInfo(pageResponse);
        return Response.success(response);

    }

    @PostMapping ("/create")
    public Response<TravelCreateResponse> create(@RequestBody TripApply tripApply) {
        // 入参校验
        this.checkTripApplyCreateParameters(tripApply);
        TravelCreateResponse response = TravelCreateResponse.builder()
                .id(tripApplyService.create(tripApply))
                .build();
        return Response.success(response);
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
    public Response<TravelDetailResponse> detail(@RequestBody TravelDeleteRequest request) {
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
            throw new BasicRunException(ErrorCodes.MISSING_PARAMETER.getCode(), COMMON);
        }
        if (StringUtils.isBlank(tripApply.getTravelCity())) {
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "差旅申请city不能为空");
        }
        if (Objects.isNull(tripApply.getDays()) || tripApply.getDays() <= NumberUtils.INTEGER_ZERO) {
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "差旅申请day不合法");
        }
        if (StringUtils.isBlank(tripApply.getCreateUser())) {
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "差旅申请创建人不能为空");
        }
    }

    /**
     * 差旅申请编辑入参校验
     * @param tripApply
     */
    public void checkTripApplyUpdateParameters(TripApply tripApply) {
        if (Objects.isNull(tripApply)) {
            throw new BasicRunException(ErrorCodes.MISSING_PARAMETER.getCode(), COMMON);
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
            throw new BasicRunException(ErrorCodes.MISSING_PARAMETER.getCode(), COMMON);
        }
        if (Objects.isNull(request.getId()) || request.getId() <= NumberUtils.LONG_ZERO) {
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "差旅申请id不合法");
        }
    }

    /**
     * 列表分页查询参数校验
     * @param request
     */
    public void checkUserListParameters(TravelListRequest request) {
        if (Objects.isNull(request)) {
            throw new BasicRunException(ErrorCodes.MISSING_PARAMETER.getCode(), "入参不能为空");
        }
        if (Objects.isNull(request.getPageNum()) || request.getPageNum() <= NumberUtils.INTEGER_ZERO) {
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "pageNum不合法");
        }
        if (Objects.isNull(request.getPageSize()) || request.getPageSize() <= NumberUtils.INTEGER_ZERO) {
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "pageSize不合法");
        }
    }

}

