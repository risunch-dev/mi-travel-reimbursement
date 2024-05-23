package com.xiaomi.info.mapper;

import com.xiaomi.info.model.TripApply;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface TripApplyMapper {
    /**
     * 插入申请数据
     * @param tripApply 申请单
     * @return 受影响的行数
     */
    Integer insert(TripApply tripApply);

    TripApply getByName(String name);
    Integer updateDetailById(
            Integer id,
            String title,
            Integer days,
            String travelCity,
            String attachMent,
            String updateUser,
            Date updateTime);

    TripApply findById(Integer id);

    Integer deleteById(Integer id);
}
