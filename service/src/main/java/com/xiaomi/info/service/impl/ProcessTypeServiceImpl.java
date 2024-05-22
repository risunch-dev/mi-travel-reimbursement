package com.xiaomi.info.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomi.info.mapper.XmProcessTypeMapper;
import com.xiaomi.info.model.process.XmProcessType;
import com.xiaomi.info.service.ProcessTypeService;
import org.springframework.stereotype.Service;

/**
 * ClassName: ProcessTypeServiceImpl
 * Package: com.xiaomi.info.service.impl
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/22 13:56
 * @Version 1.0
 */
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class ProcessTypeServiceImpl extends ServiceImpl<XmProcessTypeMapper, XmProcessType> implements ProcessTypeService {
}
