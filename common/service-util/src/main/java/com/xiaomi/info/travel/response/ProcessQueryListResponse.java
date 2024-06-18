package com.xiaomi.info.travel.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName: ProcessQueryListResponse
 * Package: com.xiaomi.info.travel.response
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/6/18 11:53
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessQueryListResponse implements Serializable {
    private List<TaskResponse> list;
}
