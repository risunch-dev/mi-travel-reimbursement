package service;

import com.google.protobuf.ServiceException;
import com.xiaomi.info.model.XmUser;
import com.xiaomi.info.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
    @Resource
    private UserService userService;

    @Test
    public void reg() {
        try {
            XmUser user = new XmUser();
            user.setName("lower");
            user.setDepartmentId(123456L);
            user.setLeaderId(1);
            user.setStatus(1);
            user.setEmail("lower@tedu.cn");
            System.out.println("注册成功！");
        } catch (Exception e) {
            System.out.println("注册失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
