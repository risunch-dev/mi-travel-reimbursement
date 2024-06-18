package com.xiaomi.info.travel.request;

import com.xiaomi.info.common.request.PageRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: TravelListResquest
 * Package: com.xiaomi.info.travel
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/18 13:17
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravelListRequest extends PageRequest implements Serializable {

    /**
     * 差旅申请名称
     */
    private String createUser;
}
