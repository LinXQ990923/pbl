package fudan.edu.pbl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author lwy
 * @since 2020-06-12
 */
public class Program implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "programID", type = IdType.AUTO)
    private Integer programID;

    @TableField("programName")
    private String programName;

    private String introduction;

    @TableField("startTime")
    private LocalDateTime startTime;

    @TableField("endTime")
    private LocalDateTime endTime;

    @TableField("courseID")
    private Integer courseID;

    @TableField("gradePolicy")
    private String gradePolicy;

    @TableField("hasDelete")
    private Integer hasDelete;

    @TableField(exist = false)
    private List<Task> taskList;

    public Integer getProgramID() {
        return programID;
    }

    public void setProgramID(Integer programID) {
        this.programID = programID;
    }
    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
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
    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }
    public String getGradePolicy() {
        return gradePolicy;
    }

    public void setGradePolicy(String gradePolicy) {
        this.gradePolicy = gradePolicy;
    }
    public Integer getHasDelete() {
        return hasDelete;
    }

    public void setHasDelete(Integer hasDelete) {
        this.hasDelete = hasDelete;
    }

    @Override
    public String toString() {
        return "Program{" +
            "programID=" + programID +
            ", programName=" + programName +
            ", introduction=" + introduction +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            ", courseID=" + courseID +
            ", gradePolicy=" + gradePolicy +
            ", hasDelete=" + hasDelete +
        "}";
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
