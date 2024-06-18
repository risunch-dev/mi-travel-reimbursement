package service;

import com.xiaomi.info.model.Reimbursement;
import com.xiaomi.info.service.ReimbursementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReimBurseMentServiceTests {
    @Resource
    private ReimbursementService reimBurseMentService;

    @Test
    public void submit() {
        try {
            Reimbursement reimBurseMent = new Reimbursement();
            reimBurseMent.setName("test004");
            reimBurseMent.setAttachment("ww");
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
            Long id = 4L;
            reimBurseMentService.deleteById(id);
            System.out.println("信息删除成功");
        } catch (Exception e) {
            System.out.println("信息删除失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getById() {
        Long id = 5L;
        Reimbursement result = reimBurseMentService.getById(id);
        System.out.println(result);
    }
}
