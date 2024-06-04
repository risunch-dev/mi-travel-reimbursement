package com.xiaomi.info.service;

import com.xiaomi.info.vo.TaskVO;

import java.util.List;

/**
 * ClassName: ITripRecordService
 * Package: com.xiaomi.info.service
 * Description:
 *
 * @Author 张芳泽
 * @Create 2024/6/4 22:30
 * @Version 1.0
 */
public interface ITripRecordService {

    void submit(Long id);

    List<TaskVO> Inquire(Long Id);

    Integer pass(Long id,String instanceID,String desc);

    Integer turnDown(Long id,String instanceID,String desc);
}
