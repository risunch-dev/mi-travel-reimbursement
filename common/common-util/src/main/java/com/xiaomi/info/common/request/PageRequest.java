package com.xiaomi.info.common.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: PageRequest
 * Package: com.xiaomi.info.common.request
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/18 13:20
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest implements Serializable {

    private Integer pageNum;

    private Integer pageSize;
}
