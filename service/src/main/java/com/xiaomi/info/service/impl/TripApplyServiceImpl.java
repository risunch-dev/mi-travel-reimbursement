package com.xiaomi.info.service.impl;

import com.xiaomi.info.common.enums.ErrorCodes;
import com.xiaomi.info.exception.BasicRunException;
import com.xiaomi.info.mapper.TripApplyMapper;
import com.xiaomi.info.model.TripApply;
import com.xiaomi.info.service.TripApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
@Service
@Slf4j
public class TripApplyServiceImpl implements TripApplyService {
    @Resource
    private TripApplyMapper tripApplyMapper;

    /**
     * 创建差旅申请
     * @param tripApply
     * @return
     */
    @Override
    public Boolean create(TripApply tripApply) {
        TripApply apply = tripApplyMapper.selectById(tripApply.getId());
        // 如果id存在，则判断status
        if(apply != null) {
            if(apply.getStatus() == 0) {
                apply.setName(tripApply.getName());
                apply.setStatus(1);
                apply.setTravelCity(tripApply.getTravelCity());
                apply.setAttachment(apply.getAttachment());
                apply.setDays(tripApply.getDays());
                apply.setAmount(tripApply.getDays() * 300);
                apply.setUpdateTime(new Date());
                apply.setUpdateUser(tripApply.getUpdateUser());
                tripApplyMapper.updateById(apply);
                return true;
            } else {
                log.error("当前差旅申请已经存在,id={}", tripApply.getId());
                throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "当前差旅申请已经存在");
            }
        }

        // id不存在则新建申请
        tripApply.setAmount(tripApply.getDays() * 300);
        tripApply.setStatus(1);
        tripApplyMapper.insert(tripApply);
        return true;
    }

    /**
     * 差旅申请编辑
     * @param tripApply 编辑内容
     * @return
     */
    @Override
    public Boolean update(TripApply tripApply) {
        tripApply.setUpdateTime(new Date());
        tripApply.setAmount(tripApply.getDays() * 300);
        tripApplyMapper.updateById(tripApply);
        return true;
    }

    /**
     * 删除申请：软删除
     * @param id 根据id删除
     */
    @Override
    public Boolean delete(Long id) {
        TripApply tripApply = tripApplyMapper.selectById(id);
        if(tripApply == null) {
            log.error("当前id对应的差旅申请为空,id={}", id);
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "当前id不存在");
        }
        tripApply.setStatus(0);
        tripApply.setUpdateTime(new Date());
        tripApplyMapper.updateById(tripApply);
        return true;
    }

    /**
     * 详情接口
     * @param id 根据id查看差旅申请详情
     * @return
     */
    @Override
    public TripApply detail(Long id) {
        return tripApplyMapper.selectById(id);
    }

}
