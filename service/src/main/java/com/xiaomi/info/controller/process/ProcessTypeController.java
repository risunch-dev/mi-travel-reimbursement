package com.xiaomi.info.controller.process;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaomi.info.model.process.XmProcessType;
import com.xiaomi.info.process.response.ProcessTypeListResponse;
import com.xiaomi.info.process.response.ProcessTypeResponse;
import com.xiaomi.info.response.Response;
import com.xiaomi.info.service.ProcessTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * ClassName: ProcessTypeController
 * Package: com.xiaomi.info.controller.process
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/22 13:58
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/process/processType")
@SuppressWarnings({"unchecked", "rawtypes"})
public class ProcessTypeController {

    @Autowired
    private ProcessTypeService processTypeService;


    @GetMapping("/findAll")
    public Response<ProcessTypeListResponse> findAll() {
        ProcessTypeListResponse response = ProcessTypeListResponse.builder()
                .xmProcessTypeList(processTypeService.list())
                .build();
        return Response.success(response);
    }

    /**
     * 获取分页列表
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/{page}/{limit}")
    public Response<ProcessTypeResponse> index(@PathVariable Long page,
                                               @PathVariable Long limit) {
        Page<XmProcessType> pageParam = new Page<>(page, limit);
        IPage<XmProcessType> pageModel = processTypeService.page(pageParam);

        ProcessTypeResponse processTypeResponse = ProcessTypeResponse.builder()
                .pageInfo(pageModel)
                .build();
        return Response.success(processTypeResponse);
    }

    @GetMapping("/get/{id}")
    public Response<XmProcessType> get(@PathVariable Long id) {
        XmProcessType processType = processTypeService.getById(id);
        return Response.success(processType);
    }

    /**
     * 新增
     * @param processType
     * @return
     */
    @PostMapping("/save")
    public Response<Boolean> save(@RequestBody XmProcessType processType) {
        processTypeService.save(processType);
        return Response.success(Boolean.TRUE);
    }

    /**
     * 修改
     * @param processType
     * @return
     */
    @PutMapping("/update")
    public Response<Boolean> updateById(@RequestBody XmProcessType processType) {
        processTypeService.updateById(processType);
        return Response.success(Boolean.TRUE);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/remove/{id}")
    public Response<Boolean> remove(@PathVariable Long id) {
        processTypeService.removeById(id);
        return Response.success(Boolean.TRUE);
    }


}
