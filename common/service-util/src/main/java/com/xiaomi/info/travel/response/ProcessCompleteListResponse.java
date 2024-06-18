package com.xiaomi.info.travel.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.activiti.engine.history.HistoricTaskInstance;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName: ProcessCompleteListResponse
 * Package: com.xiaomi.info.travel.response
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/6/18 22:45
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessCompleteListResponse implements Serializable {

    private List<HistoricTaskInstance> list;
}
