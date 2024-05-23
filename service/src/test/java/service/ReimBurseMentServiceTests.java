package com.cy.xm_travel_reimbursement.service;

import com.cy.xm_travel_reimbursement.entity.ReimBurseMent;
import com.cy.xm_travel_reimbursement.entity.TripApply;
import com.cy.xm_travel_reimbursement.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReimBurseMentServiceTests {
    @Autowired
    private IReimBurseMentService iReimBurseMentService;

    @Test
    public void submit() {
        try {
            ReimBurseMent reimBurseMent = new ReimBurseMent();
            reimBurseMent.setName("test004");
            reimBurseMent.setAttachMent("ww");
            reimBurseMent.setAmount(1000);
            reimBurseMent.setStatus(0);
            reimBurseMent.setItem("去北京");
            reimBurseMent.setApplyTime("123");
            iReimBurseMentService.submit(reimBurseMent);
            System.out.println("提交成功！");
        } catch (ServiceException e) {
            System.out.println("注册失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void deleteById() {
        try {
            Integer id = 4;
            iReimBurseMentService.deleteById(id);
            System.out.println("信息删除成功");
        } catch (ServiceException e) {
            System.out.println("信息删除失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getById() {
        Integer id = 5;
        ReimBurseMent result = iReimBurseMentService.getById(id);
        System.out.println(result);
    }
}
