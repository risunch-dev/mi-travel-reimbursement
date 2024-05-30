package com.xiaomi.info.process.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * ClassName: ProcessDetailResponse
 * Package: com.xiaomi.info.process.response
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/31 0:25
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessDetailResponse implements Serializable {
    private Map<String, Object> map;
}
