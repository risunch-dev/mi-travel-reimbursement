package com.cy.xm_travel_reimbursement.service;

import com.cy.xm_travel_reimbursement.entity.User;
import com.cy.xm_travel_reimbursement.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
    @Autowired
    private IUserService iUserService;

    @Test
    public void reg() {
        try {
            User user = new User();
            user.setName("lower");
            user.setDepartmentId(123456);
            user.setLeaderId(1);
            user.setStatus(1);
            user.setEmail("lower@tedu.cn");
            iUserService.reg(user);
            System.out.println("注册成功！");
        } catch (ServiceException e) {
            System.out.println("注册失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
