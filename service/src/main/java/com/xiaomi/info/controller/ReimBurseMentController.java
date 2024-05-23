package com.xiaomi.info.controller;

import com.xiaomi.info.model.ReimBurseMent;
import com.xiaomi.info.service.ReimBurseMentService;
import com.xiaomi.info.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static javax.security.auth.callback.ConfirmationCallback.OK;

/** 处理用户相关请求的控制器类 */
@RestController
@RequestMapping("api/reimbursement_apply")
public class ReimBurseMentController {
    @Autowired
    private ReimBurseMentService reimBurseMentService;

    @RequestMapping("creat")
    public JsonResult<String> submit(ReimBurseMent reimBurseMent) {
        // 调用业务对象执行注册
        reimBurseMentService.submit(reimBurseMent);
        // 返回
        return new JsonResult<String>(OK,"success");
    }

    @RequestMapping("{id}/change_detail")
    public JsonResult<String> changeDetail(@PathVariable("id") Integer id,String item, Integer amount, String attachMent) {

        reimBurseMentService.changeDetail(id,item,attachMent,amount);
        // 返回
        return new JsonResult<String>(OK,"Apply update successfully");
    }

    @RequestMapping("{id}/delete")
    public JsonResult<String> delete(@PathVariable("id") Integer id) {

        reimBurseMentService.deleteById(id);
        // 返回
        return new JsonResult<String>(OK,"Apply deleted successfully.");
    }

    @RequestMapping("{id}/detail")
    public JsonResult<ReimBurseMent> get(@PathVariable("id") Integer id) {

        ReimBurseMent result = reimBurseMentService.getById(id);
        // 返回
        return new JsonResult<ReimBurseMent>(OK,result);
    }

}