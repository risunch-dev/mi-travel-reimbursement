package com.xiaomi.info.service.impl;

import com.xiaomi.info.mapper.ReimbursementMapper;
import com.xiaomi.info.model.Reimbursement;
import com.xiaomi.info.service.ReimbursementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
@Service
public class ReimbursementImpl implements ReimbursementService {

    @Resource
    private ReimbursementMapper reimBurseMentMapper;

    @Override
    public void submit(Reimbursement reimBurseMent) {
        Date now = new Date();
        String name = reimBurseMent.getName();
        reimBurseMent.setCreateTime(now);
        reimBurseMent.setCreateUser(name);
        reimBurseMent.setUpdateTime(now);
        reimBurseMent.setUpdateUser(name);
        reimBurseMentMapper.insert(reimBurseMent);

    }

    @Override
    public void changeDetail(Long id, String item, String attachment, Integer amount) {
        Reimbursement result = reimBurseMentMapper.findById(id);
        String name = result.getName();
        Date now = new Date();
        reimBurseMentMapper.updateDetailById(id,item,amount,attachment,name,now);
    }

    @Override
    public void deleteById(Long id) {
        reimBurseMentMapper.findById(id);
        reimBurseMentMapper.deleteById(id);
    }

    @Override
    public Reimbursement getById(Long id) {
        return reimBurseMentMapper.findById(id);
    }



}
