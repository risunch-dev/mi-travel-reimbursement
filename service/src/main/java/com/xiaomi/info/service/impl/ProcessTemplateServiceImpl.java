package com.xiaomi.info.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomi.info.mapper.XmProcessTemplateMapper;
import com.xiaomi.info.model.process.XmProcessTemplate;
import com.xiaomi.info.model.process.XmProcessType;
import com.xiaomi.info.service.ProcessTemplateService;
import com.xiaomi.info.service.ProcessTypeService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName: ProcessTemplateServiceImpl
 * Package: com.xiaomi.info.service.impl
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/22 15:40
 * @Version 1.0
 */

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class ProcessTemplateServiceImpl extends ServiceImpl<XmProcessTemplateMapper, XmProcessTemplate> implements ProcessTemplateService {

    @Resource
    private XmProcessTemplateMapper xmProcessTemplateMapper;

    @Resource
    private ProcessTypeService processTypeService;

    @Override
    public IPage<XmProcessTemplate> selectPage(Page<XmProcessTemplate> pageParam) {
        IPage<XmProcessTemplate> page = xmProcessTemplateMapper.selectPage(pageParam, new LambdaQueryWrapper<XmProcessTemplate>()
                .orderByDesc(XmProcessTemplate::getId));
        List<XmProcessTemplate> processTemplateList = page.getRecords();

        List<Long> processTypeIdList = processTemplateList.stream().map(processTemplate -> processTemplate.getProcessTypeId()).collect(Collectors.toList());

        if(!CollectionUtils.isEmpty(processTypeIdList)) {
            Map<Long, XmProcessType> processTypeIdToProcessTypeMap = processTypeService.list(new LambdaQueryWrapper<XmProcessType>()
                    .in(XmProcessType::getId, processTypeIdList))
                    .stream()
                    .collect(Collectors.toMap(XmProcessType::getId, ProcessType -> ProcessType));
            for(XmProcessTemplate processTemplate : processTemplateList) {
                XmProcessType xmProcessType = processTypeIdToProcessTypeMap.get(processTemplate.getProcessTypeId());
                if(null != xmProcessType) {
                    processTemplate.setProcessTypeName(xmProcessType.getName());
                }
            }
        }
        return page;
    }

    /**
     * 部署流程定义（发布）
     * @param id
     */
    @Override
    public void publish(Long id) {
        XmProcessTemplate xmProcessTemplate = xmProcessTemplateMapper.selectById(id);
        xmProcessTemplate.setStatus(1);
        xmProcessTemplateMapper.updateById(xmProcessTemplate);

        //TODO 部署流程定义，后续完善
    }
}
