package com.xiaomi.info.travel.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: TravelCreateResponse
 * Package: com.xiaomi.info.travel.response
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/6/18 14:07
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravelCreateResponse implements Serializable {
    private Long id;
}
