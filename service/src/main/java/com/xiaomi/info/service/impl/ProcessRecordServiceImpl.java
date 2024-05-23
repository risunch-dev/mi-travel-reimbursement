package com.xiaomi.info.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomi.info.mapper.XmProcessRecordMapper;
import com.xiaomi.info.model.XmUser;
import com.xiaomi.info.model.process.XmProcessRecord;
import com.xiaomi.info.service.ProcessRecordService;
import com.xiaomi.info.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: ProcessRecordServiceImpl
 * Package: com.xiaomi.info.service.impl
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/23 13:55
 * @Version 1.0
 */
@Service
public class ProcessRecordServiceImpl extends ServiceImpl<XmProcessRecordMapper, XmProcessRecord> implements ProcessRecordService {

    @Autowired
    private XmProcessRecordMapper xmProcessRecordMapper;

    @Autowired
    private UserService userService;

    @Override
    public void record(Long processId, Integer status, String description, Long userId) {
        XmUser xmUser = userService.getById(userId);
        XmProcessRecord xmProcessRecord = XmProcessRecord.builder()
                .processId(processId)
                .status(status)
                .description(description)
                .operateUserId(xmUser.getId())
                .operateUser(xmUser.getName())
                .build();
        xmProcessRecordMapper.insert(xmProcessRecord);
    }
}
