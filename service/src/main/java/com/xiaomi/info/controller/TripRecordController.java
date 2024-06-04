package com.xiaomi.info.controller;

import com.xiaomi.info.service.ITripRecordService;
import com.xiaomi.info.util.JsonResult;
import com.xiaomi.info.vo.TaskVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: TripRecordController
 * Package: com.xiaomi.info.controller
 * Description:
 *
 * @Author 张芳泽
 * @Create 2024/6/4 22:27
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/travel/apply")
public class TripRecordController {

    public static final int OK = 200;

    @Resource
    private ITripRecordService tripRecordService;

    @RequestMapping("/submit")
    public JsonResult<String> submit(Long id) {

        // 调用业务对象执行注册
        tripRecordService.submit(id);
        // 返回
        return new JsonResult<String>(OK,"Submit successfully");
    }

    @RequestMapping("inquire/{id}")
    public JsonResult<List<TaskVO>> inquire(@PathVariable("id") Long id) {

        // 调用业务对象执行注册
        List<TaskVO> data = tripRecordService.Inquire(id);
        // 返回
        return new JsonResult<List<TaskVO>>(OK,data);
    }

    @RequestMapping("pass/{id}")
    public JsonResult<String> pass(@PathVariable("id") Long id,String instanceId,String desc) {
        String data;
        Integer result = tripRecordService.pass(id,instanceId,desc);
        if(result == 1)
        {
            data = "Pass the examination";
        }
        else
            data = "No qualifications or to-do list";
        // 返回
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("turn_down/{id}")
    public JsonResult<String> turnDown(@PathVariable("id") Long id, String instanceId, String desc) {

        String data;
        Integer result = tripRecordService.turnDown(id,instanceId,desc);
        if(result == 1)
        {
            data = "TurnDown the examination";
        }
        else
            data = "No qualifications or to-do list";
        // 返回
        return new JsonResult<>(OK,data);
    }
}
