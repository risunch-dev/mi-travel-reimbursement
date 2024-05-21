package com.xiaomi.info.service.impl;

import com.xiaomi.info.mapper.TripApplyMapper;
import com.xiaomi.info.model.TripApply;
import com.xiaomi.info.service.TripApplyService;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class TripApplyServiceImpl implements TripApplyService {
    private final TripApplyMapper tripApplyMapper;

    public TripApplyServiceImpl(TripApplyMapper tripApplyMapper) {
        this.tripApplyMapper = tripApplyMapper;
    }

    @Override
    public void submit(TripApply tripApply) {
        String name = tripApply.getName();
        Integer day = tripApply.getDays();
        tripApply.setAmount(day*300);
        Date now = new Date();
        tripApply.setStatus(0);
        tripApply.setCreateUser(name);
        tripApply.setCreateTime(now);
        tripApply.setUpdateUser(name);
        tripApply.setUpdateTime(now);
        Integer rows = tripApplyMapper.insert(tripApply);
        // 判断受影响的行数是否不为1
        /*if (rows != 1) {
            // 是：插入数据时出现某种错误，则抛出InsertException异常
            throw new InsertException("添加用户数据出现未知错误，请联系系统管理员");
        }*/
    }

    @Override
    public void changeDetail(Integer id, String title, Integer days,String attachMent, String travelCity) {
        TripApply result = tripApplyMapper.findById(id);
        /*if(result == null)
        {
            throw new UserNotFoundException("用户数据不存在");
        }*/
        Date now = new Date();
        Integer rows = tripApplyMapper.updateDetailById(id, title, days,travelCity,attachMent, title,now);
        /*if (rows != 1) {
            // 是：插入数据时出现某种错误，则抛出InsertException异常
            throw new InsertException("添加用户数据出现未知错误，请联系系统管理员");
        }*/
    }

    @Override
    public void deleteById(Integer id) {
        TripApply result = tripApplyMapper.findById(id);
        /*if(result == null)
        {
            throw new UserNotFoundException("用户数据不存在");
        }*/
        Integer rows = tripApplyMapper.deleteById(id);
        /*if (rows != 1) {
            // 是：插入数据时出现某种错误，则抛出InsertException异常
            throw new DeleteException("删除用户数据出现未知错误");
        }*/
    }

    @Override
    public TripApply getById(Integer id) {
        return tripApplyMapper.findById(id);
    }

}
