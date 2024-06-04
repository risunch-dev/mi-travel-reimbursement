package com.xiaomi.info.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * ClassName: TaskVO
 * Package: com.xiaomi.info.vo
 * Description:
 *
 * @Author 张芳泽
 * @Create 2024/6/4 22:31
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskVO implements Serializable {
    private String InstanceID;

    private String TaskId;

    private String name;

    private String TaskName;

    @Override
    public String toString() {
        return "TaskVO{" +
                "InstanceID='" + InstanceID + '\'' +
                ", TaskId='" + TaskId + '\'' +
                ", name='" + name + '\'' +
                ", TaskName='" + TaskName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskVO taskVO = (TaskVO) o;
        return Objects.equals(InstanceID, taskVO.InstanceID) && Objects.equals(TaskId, taskVO.TaskId) && Objects.equals(name, taskVO.name) && Objects.equals(TaskName, taskVO.TaskName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(InstanceID, TaskId, name, TaskName);
    }
}
