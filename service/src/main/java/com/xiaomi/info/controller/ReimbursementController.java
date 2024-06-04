package com.xiaomi.info.controller;

import com.xiaomi.info.model.Reimbursement;
import com.xiaomi.info.service.ReimbursementService;
import com.xiaomi.info.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static javax.security.auth.callback.ConfirmationCallback.OK;

/** 处理用户相关请求的控制器类 */
@RestController
@RequestMapping("api/reimbursement_apply")
public class ReimbursementController {
    @Resource
    private ReimbursementService reimbursementService;

    @RequestMapping("creat")
    public JsonResult<String> submit(Reimbursement reimBurseMent) {
        // 调用业务对象执行注册
        reimbursementService.submit(reimBurseMent);
        // 返回
        return new JsonResult<String>(OK,"success");
    }

    @RequestMapping("{id}/change_detail")
    public JsonResult<String> changeDetail(@PathVariable("id") Long id,String item, Integer amount, String attachMent) {

        reimbursementService.changeDetail(id,item,attachMent,amount);
        // 返回
        return new JsonResult<String>(OK,"Apply update successfully");
    }

    @RequestMapping("{id}/delete")
    public JsonResult<String> delete(@PathVariable("id") Long id) {

        reimbursementService.deleteById(id);
        // 返回
        return new JsonResult<String>(OK,"Apply deleted successfully.");
    }

    @RequestMapping("{id}/detail")
    public JsonResult<Reimbursement> get(@PathVariable("id") Long id) {

        Reimbursement result = reimbursementService.getById(id);
        // 返回
        return new JsonResult<Reimbursement>(OK,result);
    }

}