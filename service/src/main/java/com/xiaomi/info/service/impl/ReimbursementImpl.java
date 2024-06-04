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
        Integer rows = reimBurseMentMapper.insert(reimBurseMent);
        /*if (rows != 1) {
            // 是：插入数据时出现某种错误，则抛出InsertException异常
            throw new InsertException("添加用户数据出现未知错误，请联系系统管理员");
        }*/

    }

    @Override
    public void changeDetail(Long id, String item, String attachment, Integer amount) {
        Reimbursement result = reimBurseMentMapper.findById(id);
        /*if(result == null)
        {
            throw new UserNotFoundException("用户数据不存在");
        }*/
        String name = result.getName();
        Date now = new Date();
        Long rows = reimBurseMentMapper.updateDetailById(id,item,amount,attachment,name,now);
        /*if (rows != 1) {
            // 是：插入数据时出现某种错误，则抛出InsertException异常
            throw new InsertException("修改信息出现未知错误");
        }*/
    }

    @Override
    public void deleteById(Long id) {
        Reimbursement result = reimBurseMentMapper.findById(id);
        /*if(result == null)
        {
            throw new UserNotFoundException("用户数据不存在");
        }*/
        Integer rows = reimBurseMentMapper.deleteById(id);
        /*if (rows != 1) {
            // 是：插入数据时出现某种错误，则抛出InsertException异常
            throw new DeleteException("删除用户数据出现未知错误");
        }*/
    }

    @Override
    public Reimbursement getById(Long id) {
        return reimBurseMentMapper.findById(id);
    }



}
