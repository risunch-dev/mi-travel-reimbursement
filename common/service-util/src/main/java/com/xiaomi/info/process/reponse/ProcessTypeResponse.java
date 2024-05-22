package com.xiaomi.info.process.reponse;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaomi.info.model.process.XmProcessType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: ProcessTypeResponse
 * Package: com.xiaomi.info.process
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/22 14:03
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessTypeResponse implements Serializable {
    private IPage<XmProcessType> pageInfo;
}
