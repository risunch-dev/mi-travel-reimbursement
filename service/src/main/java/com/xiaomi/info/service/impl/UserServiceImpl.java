package com.xiaomi.info.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomi.info.common.enums.ErrorCodes;
import com.xiaomi.info.exception.BasicRunException;
import com.xiaomi.info.mapper.XmUserMapper;
import com.xiaomi.info.model.XmUser;
import com.xiaomi.info.response.code.ResponseCodeEnum;
import com.xiaomi.info.service.UserService;
import com.xiaomi.info.user.response.UserDetailResponse;
import com.xiaomi.info.user.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ClassName: XmUserServiceImpl
 * Package: com.xiaomi.info.service.impl
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/17 16:13
 * @Version 1.0
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<XmUserMapper, XmUser> implements UserService {

    @Resource
    private XmUserMapper xmUserMapper;

    public UserResponse userDetail(Long userId) {
        XmUser xmUser = xmUserMapper.selectOne(new LambdaQueryWrapper<XmUser>()
                .eq(XmUser::getId, userId));
        if(xmUser == null) {
            log.error("当前userId对应用户为空,userId={}", userId);
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "当前userId不存在");
        }
        UserResponse userResponse = UserResponse.builder()
                .userId(userId)
                .userName(xmUser.getName())
                .email(xmUser.getEmail())
                .departmentId(xmUser.getDepartmentId())
                .departmentName(xmUser.getDepartmentName())
                .build();
        return userResponse;
    }
}
