package com.xiaomi.info.controller;

import com.xiaomi.info.model.TripApply;
import com.xiaomi.info.service.TripApplyService;

import com.xiaomi.info.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static javax.security.auth.callback.ConfirmationCallback.OK;

/**
 * ClassName: TravelController
 * Package: com.xiaomi.info.controller
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/18 13:43
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/travel_apply")
public class TravelController {
    @Autowired
    private TripApplyService tripApplyService;

    @RequestMapping("creat")
    public JsonResult<String> submit(TripApply tripApply) {
        // 调用业务对象执行注册
        tripApplyService.submit(tripApply);
        // 返回
        return new JsonResult<String>(OK,"success");
    }

    @RequestMapping("{id}/change_detail")
    public JsonResult<String> changeDetail(@PathVariable("id") Integer id, String name, Integer days, String travelCity, String attachMent) {

        tripApplyService.changeDetail(id,name,days,attachMent,travelCity);
        // 返回
        return new JsonResult<String>(OK,"Apply update successfully");
    }

    @RequestMapping("{id}/delete")
    public JsonResult<String> delete(@PathVariable("id") Integer id) {

        tripApplyService.deleteById(id);
        // 返回
        return new JsonResult<String>(OK,"Apply deleted successfully.");
    }

    @RequestMapping("{id}/detail")
    public JsonResult<TripApply> get(@PathVariable("id") Integer id) {

        TripApply result = tripApplyService.getById(id);
        // 返回
        return new JsonResult<TripApply>(OK,result);
    }
}

