package mapper;

import com.xiaomi.info.mapper.TripApplyMapper;
import com.xiaomi.info.model.TripApply;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TripApplyMapperTests {
    @Resource
    private TripApplyMapper tripApplyMapper;
    @Test
    public void insert() {
        TripApply tripApply = new TripApply();
        tripApply.setName("test005");
        tripApply.setAttachment("www");
        tripApply.setTravelCity("上海-北京");
        tripApply.setStatus(1);
        tripApply.setDays(2);
        tripApply.setAmount(2*300);
        Date time = new Date();
        tripApply.setCreateTime(time);
        tripApply.setUpdateTime(time);
        tripApply.setCreateUser("test001");
        tripApply.setUpdateUser("test001");
        Integer rows = tripApplyMapper.insert(tripApply);
        System.out.println("rows=" + rows);
        Assert.assertTrue(true);
    }

    @Test
    public void getByName() {
        String name = "test002";
        TripApply result = tripApplyMapper.getByName(name);
        System.out.println(result);
        Assert.assertTrue(true);
    }

    @Test
    public void updateDetailById() {
        Long id = 7L;
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
        Assert.assertTrue(true);
    }

    @Test
    public void findById() {
        Long uid = 8L;
        TripApply result = tripApplyMapper.findById(uid);
        System.out.println(result);
        Assert.assertTrue(true);
    }

    @Test
    public void deleteById() {
        Long id = 7L;
        Integer result = tripApplyMapper.deleteById(id);
        System.out.println(result);
        Assert.assertTrue(true);
    }
}
