package com.xiaomi.info.process.response;

import com.xiaomi.info.model.process.XmProcessType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName: ProcessTypeListResponse
 * Package: com.xiaomi.info.process.reponse
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/22 16:13
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessTypeListResponse implements Serializable {
    private List<XmProcessType> xmProcessTypeList;
}
