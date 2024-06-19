import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xiaomi.info.StartApplication;
import com.xiaomi.info.service.RedisService;

@SpringBootTest(classes = StartApplication.class)
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void set() {
        redisService.set("test", "test");
        System.out.println(redisService.get("test"));
        Assert.assertTrue(true);
    }

    @Test
    public void get() {
        System.out.println(redisService.get("test"));
        Assert.assertTrue(true);
    }

    @Test
    public void batchGet() {
        List<String> list = new ArrayList<>();
        list.add("test");
        System.out.println(redisService.batchGet(list));
        Assert.assertTrue(true);
    }

    @Test
    public void hasKey() {
        redisService.hasKey("test");
        Assert.assertTrue(true);
    }

    @Test
    public void expire() {
        redisService.expire("test", 10);
        System.out.println(redisService.getTime("test"));
        Assert.assertTrue(true);
    }

    @Test
    public void persist() {
        redisService.persist("test");
        System.out.println(redisService.getTime("test"));
        Assert.assertTrue(true);
    }

    @Test
    public void del() {
        redisService.del("test");
        System.out.println(redisService.get("test"));
        Assert.assertTrue(true);
    }
}
