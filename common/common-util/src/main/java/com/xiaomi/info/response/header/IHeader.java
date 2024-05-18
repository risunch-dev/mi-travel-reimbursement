package com.xiaomi.info.response.header;

import java.io.Serializable;

/**
 * ClassName: IHeader
 * Package: com.xiaomi.info.common.response.header
 * Description: 请求响应头
 *
 * @Author 朱安迪
 * @Create 2024/5/18 13:03
 * @Version 1.0
 */
public interface IHeader extends Serializable {
    /**
     * 状态码
     */
    long getCode();

    /**
     * 相应的描述
     */
    String getDesc();
}
