package com.xiaomi.info.response.code;

/**
 * ClassName: ResponseCodeEnum
 * Package: com.xiaomi.info.common.response
 * Description: 返回码枚举类
 *
 * @Author 朱安迪
 * @Create 2024/5/18 13:01
 * @Version 1.0
 */
public enum ResponseCodeEnum {

    /**
     * 调用成功的统一返回码
     */
    SUCCESS(200, "success"),

    /**
     * 服务器内部错误统一返回码
     */
    SERVER_ERROR(500, "internal server error"),
    ;

    /**
     * 错误码
     */
    private final long code;

    /**
     * 错误描述
     */
    private final String desc;

    ResponseCodeEnum(long code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public long getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
