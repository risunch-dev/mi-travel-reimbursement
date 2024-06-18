package com.xiaomi.info.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: ErrorCodes
 * Package: com.xiaomi.info.common.enums
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/18 15:36
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum ErrorCodes {
    BAD_PARAMETERS(40000001, "BAD_PARAMETERS"),
    MISSING_PARAMETER(40000002, "MISSING_PARAMETER");

    private final Integer code;
    private final String desc;

}
