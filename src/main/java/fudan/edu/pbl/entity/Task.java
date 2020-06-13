package fudan.edu.pbl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lwy
 * @since 2020-06-13
 */
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "taskID", type = IdType.AUTO)
    private Integer taskID;

    @TableField("taskName")
    private String taskName;

    private String introduction;

    @TableField("startTime")
    private LocalDateTime startTime;

    @TableField("endTime")
    private LocalDateTime endTime;

    @TableField("programID")
    private Integer programID;

    @TableField("hasDelete")
    private Integer hasDelete;

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    public Integer getProgramID() {
        return programID;
    }

    public void setProgramID(Integer programID) {
        this.programID = programID;
    }
    public Integer getHasDelete() {
        return hasDelete;
    }

    public void setHasDelete(Integer hasDelete) {
        this.hasDelete = hasDelete;
    }

    @Override
    public String toString() {
        return "Task{" +
            "taskID=" + taskID +
            ", taskName=" + taskName +
            ", introduction=" + introduction +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            ", programID=" + programID +
            ", hasDelete=" + hasDelete +
        "}";
    }
}
