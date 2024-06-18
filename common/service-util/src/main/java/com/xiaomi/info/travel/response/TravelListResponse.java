package com.xiaomi.info.travel.response;

import com.xiaomi.info.common.response.PageResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: TravelListResponse
 * Package: com.xiaomi.info.travel.response
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/18 13:27
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravelListResponse implements Serializable {

    private PageResponse pageInfo;
}
