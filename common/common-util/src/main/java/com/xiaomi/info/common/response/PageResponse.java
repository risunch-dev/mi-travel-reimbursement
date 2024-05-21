package com.xiaomi.info.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: PageResponse
 * Package: com.xiaomi.info.common.response
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/18 13:25
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponse implements Serializable {

    private Integer pageNum;

    private Integer pageSize;

    private Integer total;

    private Integer pages;
}
