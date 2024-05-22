package com.xiaomi.info.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomi.info.mapper.XmProcessMapper;
import com.xiaomi.info.model.process.XmProcess;
import com.xiaomi.info.process.response.ProcessResponse;
import com.xiaomi.info.process.request.ProcessQueryRequest;
import com.xiaomi.info.service.ProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: ProcessServiceImpl
 * Package: com.xiaomi.info.service.impl
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/22 20:16
 * @Version 1.0
 */
@Slf4j
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class ProcessServiceImpl extends ServiceImpl<XmProcessMapper, XmProcess> implements ProcessService {

    @Autowired
    private XmProcessMapper xmProcessMapper;

    @Override
    public IPage<ProcessResponse> selectPage(Page<ProcessResponse> pageParam, ProcessQueryRequest processQueryRequest) {
        IPage<ProcessResponse> page = xmProcessMapper.selectPage(pageParam, processQueryRequest, null);
        return page;
    }
}
