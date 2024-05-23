package com.cy.xm_travel_reimbursement.mapper;

import com.cy.xm_travel_reimbursement.entity.TripApply;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TripApplyMapperTests {
    @Autowired
    private TripApplyMapper tripApplyMapper;
    @Test
    public void insert() {
        TripApply tripApply = new TripApply();
        tripApply.setTitle("test005");
        tripApply.setAttachMent("www");
        tripApply.setTravelCity("上海-北京");
        tripApply.setStatus(1);
        tripApply.setDays(2);
        tripApply.setAmount(2*300);
        Date time = new Date();
        tripApply.setCreatedTime(time);
        tripApply.setUpdateTime(time);
        tripApply.setCreatedUser("test001");
        tripApply.setUpdateUser("test001");
        Integer rows = tripApplyMapper.insert(tripApply);
        System.out.println("rows=" + rows);
    }

    @Test
    public void getByName()
    {
        String name = "test002";
        TripApply result = tripApplyMapper.getByName(name);
        System.out.println(result);
    }

    @Test
    public void updateDetailById() {
        Integer id = 7;
        String title = "test008";
        String updateUser = "管理员";
        Date updateTime = new Date();
        String travelCity = "武汉-北京";
        Integer days = 2;
        String attachMent = "qq";
        Integer rows = tripApplyMapper.updateDetailById(id,
                title,
                days,
                travelCity,
                attachMent,
                updateUser,
                updateTime);
        System.out.println("rows=" + rows);
    }

    @Test
    public void findById() {
        Integer uid = 8;
        TripApply result = tripApplyMapper.findById(uid);
        System.out.println(result);
    }

    @Test
    public void deleteById() {
        Integer id = 7;
        Integer result = tripApplyMapper.deleteById(id);
        System.out.println(result);
    }
}
