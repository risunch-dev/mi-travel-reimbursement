package mapper;

import com.xiaomi.info.mapper.ReimbursementMapper;
import com.xiaomi.info.model.Reimbursement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReimBurseMentMapperTests {
    @Resource
    private ReimbursementMapper reimBurseMentMapper;
    @Test
    public void insert() {
        Reimbursement reimBurseMent = new Reimbursement();
        reimBurseMent.setName("test002");
        reimBurseMent.setStatus(1);
        reimBurseMent.setAttachment("www");
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
        Long id = 1L;
        String item = "test007";
        Integer amount = 1000;
        String updateUser = "管理员";
        Date updateTime = new Date();
        String attachment = "qq";
        Long rows = reimBurseMentMapper.updateDetailById(id,
                item,
                amount,
                attachment,
                updateUser,
                updateTime);
        System.out.println("rows=" + rows);
    }

   @Test
    public void findById() {
        Long id = 1L;
        Reimbursement result = reimBurseMentMapper.findById(id);
        System.out.println(result);
    }

   @Test
    public void deleteById() {
        Long id = 5L;
        Integer result = reimBurseMentMapper.deleteById(id);
        System.out.println(result);
    }
}
