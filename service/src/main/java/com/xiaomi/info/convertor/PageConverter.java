package com.xiaomi.info.convertor;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaomi.info.common.entity.PageResult;

/**
 * ClassName: PageConverter
 * Package: com.xiaomi.info.convertor
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/6/18 16:32
 * @Version 1.0
 */
public class PageConverter {
    private PageConverter() {
    }

    public static <T> PageResult<T> transform(Page<T> dbPage) {
        if (null == dbPage) {
            return null;
        }
        PageResult<T> result = new PageResult<>();
        result.setData(dbPage.getRecords());
        com.xiaomi.info.common.entity.Page page = new com.xiaomi.info.common.entity.Page();
        page.setPageNum((int) dbPage.getCurrent());
        page.setPageSize((int) dbPage.getSize());
        page.setTotal((int) dbPage.getTotal());
        page.calPages();
        result.setPageInfo(page);
        return result;
    }
}
