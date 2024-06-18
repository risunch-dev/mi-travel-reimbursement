package com.xiaomi.info.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName: DateUtils
 * Package: com.xiaomi.info.common.utils
 * Description: 时间戳工具类
 *
 * @Author 朱安迪
 * @Create 2024/6/18 14:23
 * @Version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {
    /**
     * 默认日期时间格式
     */
    public static final String DEFAULT_DATA_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String EMAIL_DATE_FORMAT = "yyyy年MM月dd日";

    public static final String DATE_SECONDS_FORMAT = "yyyyMMddHHmmss";

    /**
     * Date时间转换为默认日期格式字符串
     * @param date Date
     * @return "yyyy-MM-dd HH:mm:ss"
     */
    public static String getDefaultDateString(Date date) {
        SimpleDateFormat defaultDateFormat = new SimpleDateFormat(DEFAULT_DATA_FORMAT);
        return defaultDateFormat.format(date);
    }
}
