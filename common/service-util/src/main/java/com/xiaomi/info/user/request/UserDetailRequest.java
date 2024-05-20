package com.xiaomi.info.user.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: UserDetailRequest
 * Package: com.xiaomi.info.user.request
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/18 13:58
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailRequest implements Serializable {

    private Long userId;
}
