package com.xiaomi.info.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomi.info.mapper.XmProcessTypeMapper;
import com.xiaomi.info.model.process.XmProcessTemplate;
import com.xiaomi.info.model.process.XmProcessType;
import com.xiaomi.info.service.ProcessTemplateService;
import com.xiaomi.info.service.ProcessTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private ProcessTemplateService processTemplateService;

    /**
     * 查询所有审批分类及其审批模板
     * @return
     */
    @Override
    public List<XmProcessType> findProcessType() {
        // 查询所有审批分类
        List<XmProcessType> processTypeList = baseMapper.selectList(null);

        for (XmProcessType processType : processTypeList) {
            // 根据审批分类id查询对应审批模板
            List<XmProcessTemplate> processTemplateList = processTemplateService.list(new LambdaQueryWrapper<XmProcessTemplate>()
                    .eq(XmProcessTemplate::getProcessTypeId, processType.getId()));

            //根据审批分类id查询对应审批模板List封装到审批分类对象里面
            processType.setProcessTemplateList(processTemplateList);
        }
        return processTypeList;
    }
}
