package com.xiaomi.info.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaomi.info.model.XmUser;
import com.xiaomi.info.user.request.UserDetailRequest;
import com.xiaomi.info.user.response.UserDetailResponse;
import com.xiaomi.info.user.response.UserResponse;

/**
 * ClassName: XmUserService
 * Package: com.xiaomi.info.service
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/17 16:12
 * @Version 1.0
 */
public interface UserService extends IService<XmUser> {
    UserResponse userDetail(Long userId);

    XmUser getByUserName(String userName);
}
