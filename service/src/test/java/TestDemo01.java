import com.xiaomi.info.StartApplication;
import com.xiaomi.info.mapper.XmUserMapper;
import com.xiaomi.info.model.XmUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

/**
 * ClassName: TestDemo01
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/17 15:12
 * @Version 1.0
 */
@ContextConfiguration
@SpringBootTest(classes = StartApplication.class)
public class TestDemo01 {
    @Autowired
    public XmUserMapper xmUserMapper;

    @Test
    public void getAll() {
        List<XmUser> list = xmUserMapper.selectList(null);
        System.out.println(list);
    }
}
