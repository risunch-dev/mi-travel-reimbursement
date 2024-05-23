package service;

import com.google.protobuf.ServiceException;
import com.xiaomi.info.model.ReimBurseMent;
import com.xiaomi.info.service.ReimBurseMentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReimBurseMentServiceTests {
    @Resource
    private ReimBurseMentService reimBurseMentService;

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
            reimBurseMentService.submit(reimBurseMent);
            System.out.println("提交成功！");
        } catch (Exception e) {
            System.out.println("注册失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void deleteById() {
        try {
            Integer id = 4;
            reimBurseMentService.deleteById(id);
            System.out.println("信息删除成功");
        } catch (Exception e) {
            System.out.println("信息删除失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getById() {
        Integer id = 5;
        ReimBurseMent result = reimBurseMentService.getById(id);
        System.out.println(result);
    }
}
