package com.xiaomi.info.service;

import com.xiaomi.info.model.TripApply;

public interface TripApplyService {

        void submit(TripApply tripApply);

        void changeDetail(Integer id, String title, Integer days,String attachMent, String travelCity);

        void deleteById(Integer id);

        TripApply getById(Integer id);


}
