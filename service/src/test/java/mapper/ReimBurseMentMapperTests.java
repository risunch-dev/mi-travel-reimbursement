package mapper;

import com.xiaomi.info.mapper.ReimBurseMentMapper;
import com.xiaomi.info.model.ReimBurseMent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReimBurseMentMapperTests {
    @Resource
    private ReimBurseMentMapper reimBurseMentMapper;
    @Test
    public void insert() {
        ReimBurseMent reimBurseMent = new ReimBurseMent();
        reimBurseMent.setName("test002");
        reimBurseMent.setStatus(1);
        reimBurseMent.setAttachMent("www");
        reimBurseMent.setAmount(100);
        reimBurseMent.setItem("出差");
        Date time = new Date();
        reimBurseMent.setCreateTime(time);
        reimBurseMent.setUpdateTime(time);
        reimBurseMent.setCreateUser("admin");
        reimBurseMent.setUpdateUser("admin");
        reimBurseMent.setApplyTime("123");
        Integer rows = reimBurseMentMapper.insert(reimBurseMent);
        System.out.println("rows=" + rows);
    }

   /* @Test
    public void getByName()
    {
        String name = "test002";
        TripApply result = tripApplyMapper.getByName(name);
        System.out.println(result);
    }*/

    @Test
    public void updateDetailById() {
        Integer id = 1;
        String item = "test007";
        Integer amount = 1000;
        String updateUser = "管理员";
        Date updateTime = new Date();
        String attachMent = "qq";
        Integer rows = reimBurseMentMapper.updateDetailById(id,
                item,
                amount,
                attachMent,
                updateUser,
                updateTime);
        System.out.println("rows=" + rows);
    }

   @Test
    public void findById() {
        Integer id = 1;
        ReimBurseMent result = reimBurseMentMapper.findById(id);
        System.out.println(result);
    }

   @Test
    public void deleteById() {
        Integer id = 5;
        Integer result = reimBurseMentMapper.deleteById(id);
        System.out.println(result);
    }
}
