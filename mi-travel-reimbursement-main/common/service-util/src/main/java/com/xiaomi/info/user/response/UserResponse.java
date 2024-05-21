package com.xiaomi.info.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: UserResponse
 * Package: com.xiaomi.info.user.response
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/18 13:45
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse implements Serializable {

    private Long userId;

    private String userName;

    private String email;

    private Long departmentId;

    private String departmentName;
}
