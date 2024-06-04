package com.xiaomi.info.mapper;

import com.xiaomi.info.model.Reimbursement;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface ReimbursementMapper {
    Integer insert(Reimbursement reimbursement);

    Long updateDetailById(
            Long id,
            String item,
            Integer amount,
            String attachment,
            String updateUser,
            Date updateTime);

    Reimbursement findById(Long id);

    Integer deleteById(Long id);
}


