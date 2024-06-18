package com.xiaomi.info.service;

import com.xiaomi.info.common.entity.PageResult;
import com.xiaomi.info.model.TripApply;
import com.xiaomi.info.travel.response.TravelDetailResponse;

public interface TripApplyService {
        PageResult<TripApply> list(String createUser, Integer pageNum, Integer pageSize);

        Long create(TripApply tripApply);

        Boolean update(TripApply tripApply);

        Boolean delete(Long id);

        TravelDetailResponse detail(Long id);


}
