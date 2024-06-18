package com.xiaomi.info.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaomi.info.common.entity.PageResult;
import com.xiaomi.info.common.enums.ErrorCodes;
import com.xiaomi.info.common.enums.StatusEnum;
import com.xiaomi.info.common.utils.DateUtils;
import com.xiaomi.info.convertor.PageConverter;
import com.xiaomi.info.exception.BasicRunException;
import com.xiaomi.info.mapper.TripApplyMapper;
import com.xiaomi.info.model.TripApply;
import com.xiaomi.info.service.TripApplyService;
import com.xiaomi.info.travel.response.TravelDetailResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
@Service
@Slf4j
public class TripApplyServiceImpl implements TripApplyService {
    @Resource
    private TripApplyMapper tripApplyMapper;

    @Override
    public PageResult<TripApply> list(String createUser, Integer pageNum, Integer pageSize) {
        // 分页查询
        Page<TripApply> searchPage = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<TripApply> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TripApply::getStatus, StatusEnum.ENABLE.getCode())
                .orderByAsc(TripApply::getId);
        if (StringUtils.isNoneBlank(createUser)) {
            wrapper.eq(TripApply::getCreateUser, createUser);
        }
        Page<TripApply> tripApplyPage = tripApplyMapper.selectPage(searchPage, wrapper);

        return PageConverter.transform(tripApplyPage);
    }

    /**
     * 创建差旅申请
     * @param tripApply
     * @return
     */
    @Override
    public Long create(TripApply tripApply) {
        TripApply apply = tripApplyMapper.selectById(tripApply.getId());
        // 如果id存在，则判断status
        if(apply != null) {
            if(apply.getStatus() == StatusEnum.DISABLE.getCode()) {
                apply.setName(tripApply.getName());
                apply.setStatus(StatusEnum.ENABLE.getCode());
                apply.setTravelCity(tripApply.getTravelCity());
                apply.setAttachment(apply.getAttachment());
                apply.setDays(tripApply.getDays());
                apply.setAmount(tripApply.getDays() * 300);
                apply.setUpdateTime(new Date());
                apply.setUpdateUser(tripApply.getUpdateUser());
                tripApplyMapper.updateById(apply);
                return apply.getId();
            } else {
                log.error("当前差旅申请已经存在,id={}", tripApply.getId());
                throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "当前差旅申请已经存在");
            }
        }

        // id不存在则新建申请
        tripApply.setAmount(tripApply.getDays() * 300);
        tripApply.setStatus(StatusEnum.ENABLE.getCode());
        tripApplyMapper.insert(tripApply);
        return tripApply.getId();
    }

    /**
     * 差旅申请编辑
     * @param tripApply 编辑内容
     * @return
     */
    @Override
    public Boolean update(TripApply tripApply) {
        tripApply.setUpdateTime(new Date());
        if(tripApply.getDays() != null) {
            tripApply.setAmount(tripApply.getDays() * 300);
        }
        tripApplyMapper.updateById(tripApply);
        return Boolean.TRUE;
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
        tripApply.setStatus(StatusEnum.DISABLE.getCode());
        tripApply.setUpdateTime(new Date());
        tripApplyMapper.updateById(tripApply);
        return Boolean.TRUE;
    }

    /**
     * 详情接口
     * @param id 根据id查看差旅申请详情
     * @return
     */
    @Override
    public TravelDetailResponse detail(Long id) {
        TripApply tripApply = tripApplyMapper.selectById(id);
        TravelDetailResponse response = TravelDetailResponse.builder()
                .id(tripApply.getId())
                .name(tripApply.getName())
                .status(tripApply.getStatus())
                .travelCity(tripApply.getTravelCity())
                .attachment(tripApply.getAttachment())
                .days(tripApply.getDays())
                .amount(tripApply.getAmount())
                .createTime(DateUtils.getDefaultDateString(tripApply.getCreateTime()))
                .createUser(tripApply.getCreateUser())
                .updateTime(DateUtils.getDefaultDateString(tripApply.getUpdateTime()))
                .updateUser(tripApply.getUpdateUser())
                .build();
        return response;
    }

}
