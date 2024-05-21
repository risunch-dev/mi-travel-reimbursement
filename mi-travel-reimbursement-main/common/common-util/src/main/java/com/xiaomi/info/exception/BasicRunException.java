package com.xiaomi.info.exception;

/**
 * ClassName: BasicRunException
 * Package: com.xiaomi.info.exception
 * Description: 作为演示全局异常处理使用
 *
 * @Author 朱安迪
 * @Create 2024/5/18 15:30
 * @Version 1.0
 */
public class BasicRunException extends RuntimeException {

    private final String msg;

    private final int code;

    public BasicRunException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
