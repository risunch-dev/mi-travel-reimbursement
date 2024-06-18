package com.xiaomi.info.common.enums;

import lombok.Getter;

/**
 * ClassName: StatusEnum
 * Package: com.xiaomi.info.common.enums
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/6/18 13:43
 * @Version 1.0
 */
@Getter
public enum StatusEnum {
    DISABLE(0),
    ENABLE(1),
    ;

    StatusEnum(Integer code) {
        this.code = code;
    }

    private final Integer code;
}
