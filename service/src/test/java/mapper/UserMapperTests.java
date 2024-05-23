package com.cy.xm_travel_reimbursement.mapper;

import com.cy.xm_travel_reimbursement.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void insert() {
        User user = new User();
        user.setName("test003");
        user.setLeaderId(1234);
        user.setDepartmentId(1234);
        user.setEmail("dos2");
        user.setStatus(1);
        Date time = new Date();
        user.setCreatedTime(time);
        user.setUpdateTime(time);
        user.setCreatedUser("test001");
        user.setUpdateUser("test001");
        Integer rows = userMapper.insert(user);
        System.out.println("rows=" + rows);
    }

    @Test
    public void findByName() {
        String username = "test002";
        User result = userMapper.findByName(username);
        System.out.println(result);
    }
}
