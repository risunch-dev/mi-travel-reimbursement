package com.xiaomi.info.mapper;

import com.xiaomi.info.entity.TripRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: TripRecordMapper
 * Package: com.xiaomi.info.mapper
 * Description:
 *
 * @Author 张芳泽
 * @Create 2024/6/4 22:34
 * @Version 1.0
 */
@Mapper
public interface TripRecordMapper {
    /**
     * 插入申请数据
     * @param tripRecord 申请单
     * @return 受影响的行数
     */
    Integer insert(TripRecord tripRecord);
}
