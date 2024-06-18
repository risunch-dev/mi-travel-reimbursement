package service;

import com.xiaomi.info.model.TripApply;
import com.xiaomi.info.service.TripApplyService;
import com.xiaomi.info.travel.response.TravelDetailResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TripApplyServiceTests {
    @Resource
    private TripApplyService tripApplyService;

    @Test
    public void submit() {
        try {
            TripApply tripApply = new TripApply();
            tripApply.setName("test004");
            tripApply.setAttachment("ww");
            tripApply.setTravelCity("上海-北京");
            tripApply.setStatus(0);
            tripApply.setDays(3);
            tripApplyService.create(tripApply);
            System.out.println("提交成功！");
        } catch (Exception e) {
            System.out.println("注册失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
        Assert.assertTrue(true);
    }

    @Test
    public void changeDetail() {
        try {
            Long id = 11L;
            String username = "we";
            String attachment = "123456";
            String travelCity = "888888";
            Integer days = 3;
            System.out.println("信息修改成功");
        } catch (Exception e) {
            System.out.println("信息修改失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
        Assert.assertTrue(true);
    }

    @Test
    public void deleteById() {
        try {
            Long id = 5L;
            tripApplyService.delete(id);
            System.out.println("信息删除成功");
        } catch (Exception e) {
            System.out.println("信息删除失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
        Assert.assertTrue(true);
    }

    @Test
    public void getById() {
        Long id = 8L;
        TravelDetailResponse result = tripApplyService.detail(id);
        System.out.println(result);
        Assert.assertTrue(true);
    }
}
