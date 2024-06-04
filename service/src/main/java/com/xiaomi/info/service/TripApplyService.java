package com.xiaomi.info.service;

import com.xiaomi.info.model.TripApply;

public interface TripApplyService {

        void submit(TripApply tripApply);

        void changeDetail(Long id, String title, Integer days,String attachMent, String travelCity);

        void deleteById(Long id);

        TripApply getById(Long id);


}
