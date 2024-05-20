import com.xiaomi.info.StartApplication;
import com.xiaomi.info.model.XmUser;
import com.xiaomi.info.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * ClassName: TestDemo2
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/17 16:34
 * @Version 1.0
 */
@SpringBootTest(classes = StartApplication.class)
public class TestDemo2 {

    @Autowired
    public UserService userService;

    @Test
    public void getAll() {
        List<XmUser> list = userService.list();
        System.out.println(list);
    }
}
