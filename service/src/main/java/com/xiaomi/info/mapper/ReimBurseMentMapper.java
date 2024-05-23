package com.xiaomi.info.mapper;

import com.xiaomi.info.model.ReimBurseMent;

import java.util.Date;


public interface ReimBurseMentMapper {
        Integer insert(ReimBurseMent reimBurseMent);

        Integer updateDetailById(
                Integer id,
                String item,
                Integer amount,
                String attachMent,
                String updateUser,
                Date updateTime);

        ReimBurseMent findById(Integer id);

        Integer deleteById(Integer id);
}


