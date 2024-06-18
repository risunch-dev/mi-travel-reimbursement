package com.xiaomi.info.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaomi.info.model.process.XmProcess;
import com.xiaomi.info.process.response.ProcessResponse;
import com.xiaomi.info.process.request.ProcessQueryRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * ClassName: XmProcessMapper
 * Package: com.xiaomi.info.mapper
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/22 20:02
 * @Version 1.0
 */
@Mapper
public interface XmProcessMapper extends BaseMapper<XmProcess> {

    IPage<ProcessResponse> selectPage(Page<ProcessResponse> page,
                                      @Param("vo") ProcessQueryRequest processQueryRequest,
                                      @Param("ew") LambdaQueryWrapper queryWrapper);
}
