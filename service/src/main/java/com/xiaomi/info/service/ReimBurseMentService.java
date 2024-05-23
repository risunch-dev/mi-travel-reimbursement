package com.xiaomi.info.service;

import com.xiaomi.info.model.ReimBurseMent;

public interface ReimBurseMentService {
    void submit(ReimBurseMent reimBurseMent);

    void changeDetail(Integer id, String item, String attachMent, Integer amount);

    void deleteById(Integer id);

    ReimBurseMent getById(Integer id);
}
