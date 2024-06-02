package mapper;

import com.xiaomi.info.mapper.XmUserMapper;
import com.xiaomi.info.model.XmUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTests {
    @Resource
    private XmUserMapper xmUserMapper;
    @Test
    public void insert() {
        XmUser user = new XmUser();
        user.setName("test003");
        user.setLeaderId(1234);
        user.setDepartmentId(1234L);
        user.setEmail("dos2");
        user.setStatus(1);
        Date time = new Date();
        user.setCreateTime(time);
        user.setUpdateTime(time);
        user.setCreateUser("test001");
        user.setUpdateUser("test001");
        Integer rows = xmUserMapper.insert(user);
        System.out.println("rows=" + rows);
    }

    @Test
    public void findByName() {
        String username = "test002";
    }
}
