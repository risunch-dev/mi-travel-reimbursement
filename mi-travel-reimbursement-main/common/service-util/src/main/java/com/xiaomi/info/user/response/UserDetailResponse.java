package com.xiaomi.info.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName: UserListResponse
 * Package: com.xiaomi.info.user.response
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/18 13:44
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailResponse implements Serializable {

    private List<UserResponse> userList;
}
