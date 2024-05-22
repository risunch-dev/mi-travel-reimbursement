package com.xiaomi.info.controller.process;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaomi.info.common.enums.ErrorCodes;
import com.xiaomi.info.exception.BasicRunException;
import com.xiaomi.info.model.process.XmProcessTemplate;
import com.xiaomi.info.process.response.ProcessFileResponse;
import com.xiaomi.info.response.Response;
import com.xiaomi.info.service.ProcessTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: ProcessTemplateController
 * Package: com.xiaomi.info.controller.process
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/22 15:47
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/process/processTemplate")
@SuppressWarnings({"unchecked", "rawtypes"})
public class ProcessTemplateController {
    @Autowired
    private ProcessTemplateService processTemplateService;

    /**
     * 发布
     * @param id
     * @return
     */
    @GetMapping("/publish/{id}")
    public Response<Boolean> publish(@PathVariable Long id) {
        processTemplateService.publish(id);
        return Response.success();
    }

    @PostMapping("/uploadProcessDefinition")
    public Response<ProcessFileResponse> uploadProcessDefinition(MultipartFile file) throws FileNotFoundException {
        String path = new File(ResourceUtils.getURL("classpath:").getPath()).getAbsolutePath();

        String fileName = file.getOriginalFilename();
        // 上传目录
        File tempFile = new File(path + "/processes/");
        // 判断目录是否存着
        if (!tempFile.exists()) {
            tempFile.mkdirs();//创建目录
        }
        // 创建空文件用于写入文件
        File imageFile = new File(path + "/processes/" + fileName);
        // 保存文件流到本地
        try {
            file.transferTo(imageFile);
        } catch (IOException e) {
            log.error("文件上传失败");
            throw new BasicRunException(ErrorCodes.BAD_PARAMETERS.getCode(), "文件上传失败");
        }

        Map<String, Object> map = new HashMap<>();
        //根据上传地址后续部署流程定义，文件名称为流程定义的默认key
        map.put("processDefinitionPath", "processes/" + fileName);
        map.put("processDefinitionKey", fileName.substring(0, fileName.lastIndexOf(".")));
        ProcessFileResponse response = ProcessFileResponse.builder()
                .map(map)
                .build();
        return Response.success(response);
    }


    /**
     * 分页列表
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/{page}/{limit}")
    public Response<IPage<XmProcessTemplate>> index(@PathVariable Long page,
                                                    @PathVariable Long limit) {
        Page<XmProcessTemplate> pageParam = new Page<>(page, limit);
        IPage<XmProcessTemplate> pageModel = processTemplateService.selectPage(pageParam);
        return Response.success(pageModel);
    }

    /**
     * 根据id查询模板
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public Response<XmProcessTemplate> get(@PathVariable Long id) {
        XmProcessTemplate processTemplate = processTemplateService.getById(id);
        return Response.success(processTemplate);
    }

    /**
     * 新建
     * @param processTemplate
     * @return
     */
    @PostMapping("/save")
    public Response<Boolean> save(@RequestBody XmProcessTemplate processTemplate) {
        processTemplateService.save(processTemplate);
        return Response.success(Boolean.TRUE);
    }

    /**
     * 修改
     * @param processTemplate
     * @return
     */
    @PutMapping("/update")
    public Response<Boolean> updateById(@RequestBody XmProcessTemplate processTemplate) {
        processTemplateService.updateById(processTemplate);
        return Response.success(Boolean.TRUE);
    }

    @DeleteMapping("/remove/{id}")
    public Response<Boolean> remove(@PathVariable Long id) {
        processTemplateService.removeById(id);
        return Response.success(Boolean.TRUE);
    }
}
