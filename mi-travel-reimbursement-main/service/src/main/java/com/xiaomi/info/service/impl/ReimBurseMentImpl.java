package com.xiaomi.info.service.impl;

import com.xiaomi.info.mapper.ReimBurseMentMapper;
import com.xiaomi.info.model.ReimBurseMent;
import com.xiaomi.info.service.ReimBurseMentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class ReimBurseMentImpl implements ReimBurseMentService {

    @Autowired
    private ReimBurseMentMapper reimBurseMentMapper;
    @Override
    public void submit(ReimBurseMent reimBurseMent) {
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
    public void changeDetail(Integer id, String item, String attachMent, Integer amount) {
        ReimBurseMent result = reimBurseMentMapper.findById(id);
        /*if(result == null)
        {
            throw new UserNotFoundException("用户数据不存在");
        }*/
        String name = result.getName();
        Date now = new Date();
        Integer rows = reimBurseMentMapper.updateDetailById(id,item,amount,attachMent,name,now);
        /*if (rows != 1) {
            // 是：插入数据时出现某种错误，则抛出InsertException异常
            throw new InsertException("修改信息出现未知错误");
        }*/
    }

    @Override
    public void deleteById(Integer id) {
        ReimBurseMent result = reimBurseMentMapper.findById(id);
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
    public ReimBurseMent getById(Integer id) {
        return reimBurseMentMapper.findById(id);
    }



}
