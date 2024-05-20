package com.xiaomi.info.response.header;

import lombok.Data;

/**
 * ClassName: DefaultHeader
 * Package: com.xiaomi.info.common.response.header
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/18 13:04
 * @Version 1.0
 */
@Data
public class DefaultHeader implements IHeader {

    private long code;

    private String desc;

    public DefaultHeader(long code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
