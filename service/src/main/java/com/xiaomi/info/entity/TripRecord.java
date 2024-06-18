package com.xiaomi.info.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

/**
 * ClassName: TripRecord
 * Package: com.xiaomi.info.entity
 * Description:
 *
 * @Author 张芳泽
 * @Create 2024/6/4 22:34
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripRecord {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    private Long userId;
    private Integer planId;

    private Integer type;
    private Integer status;

    private String reason;

    private Date createTime;

    private Date updateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripRecord that = (TripRecord) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(planId, that.planId) && Objects.equals(type, that.type) && Objects.equals(status, that.status) && Objects.equals(reason, that.reason) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, planId, type, status, reason, createTime, updateTime);
    }

    @Override
    public String toString() {
        return "TripRecord{" +
                "id=" + id +
                ", userId=" + userId +
                ", planId=" + planId +
                ", type=" + type +
                ", status=" + status +
                ", reason='" + reason + '\'' +
                ", creatTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
