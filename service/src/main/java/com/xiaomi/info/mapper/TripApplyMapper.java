package com.xiaomi.info.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaomi.info.model.TripApply;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface TripApplyMapper extends BaseMapper<TripApply> {

    TripApply getByName(String name);
    Integer updateDetailById(
            Long id,
            String title,
            Integer days,
            String travelCity,
            String attachment,
            String updateUser,
            Date updateTime);

    TripApply findById(Long id);

    Integer deleteById(Long id);
}
