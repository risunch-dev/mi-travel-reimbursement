package com.xiaomi.info.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: Page
 * Package: com.xiaomi.info.common.entity
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/6/18 16:01
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Page implements Serializable {
    private Integer pageNum;
    private Integer pageSize;
    private Integer total;
    private Integer pages;

    public void calPages() {
        this.pages = this.total % this.pageSize == 0 ? this.total / this.pageSize : this.total / this.pageSize + 1;
    }
}
