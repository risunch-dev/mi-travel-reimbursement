package com.xiaomi.info.travel.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: TaskResponse
 * Package: com.xiaomi.info.travel.response
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/6/18 11:54
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskResponse implements Serializable {

    private String InstanceID;

    private String TaskId;

    private String name;

    private String TaskName;
}
