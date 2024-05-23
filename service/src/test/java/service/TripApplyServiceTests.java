package com.cy.xm_travel_reimbursement.service;

import com.cy.xm_travel_reimbursement.entity.TripApply;
import com.cy.xm_travel_reimbursement.entity.User;
import com.cy.xm_travel_reimbursement.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TripApplyServiceTests {
    @Autowired
    private ITripApplyService iTripApplyService;

    @Test
    public void submit() {
        try {
            TripApply tripApply = new TripApply();
            tripApply.setTitle("test004");
            tripApply.setAttachMent("ww");
            tripApply.setTravelCity("上海-北京");
            tripApply.setStatus(0);
            tripApply.setDays(3);
            iTripApplyService.submit(tripApply);
            System.out.println("提交成功！");
        } catch (ServiceException e) {
            System.out.println("注册失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void changeDetail() {
        try {
            Integer id = 11;
            String username = "we";
            String attachMent = "123456";
            String travelCity = "888888";
            Integer days = 3;
            iTripApplyService.changeDetail(id, username, days,travelCity, attachMent);
            System.out.println("信息修改成功");
        } catch (ServiceException e) {
            System.out.println("信息修改失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deleteById() {
        try {
            Integer id = 5;
            iTripApplyService.deleteById(id);
            System.out.println("信息删除成功");
        } catch (ServiceException e) {
            System.out.println("信息删除失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getById() {
        Integer id = 8;
        TripApply result = iTripApplyService.getById(id);
        System.out.println(result);
    }
}
