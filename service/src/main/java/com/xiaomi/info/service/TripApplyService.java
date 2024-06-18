package com.xiaomi.info.service;

import com.xiaomi.info.model.TripApply;

public interface TripApplyService {

        Boolean create(TripApply tripApply);

        Boolean update(TripApply tripApply);

        Boolean delete(Long id);

        TripApply detail(Long id);


}
