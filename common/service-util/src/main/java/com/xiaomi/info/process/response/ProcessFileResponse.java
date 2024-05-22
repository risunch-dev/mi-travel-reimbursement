package com.xiaomi.info.process.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * ClassName: ProcessFileResponse
 * Package: com.xiaomi.info.process.reponse
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/22 16:50
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessFileResponse implements Serializable {
    private Map<String, Object> map;
}
