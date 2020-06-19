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
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "courseID", type = IdType.AUTO)
    private Integer courseID;

    @TableField("courseName")
    private String courseName;

    private String introduction;

    @TableField("imgPath")
    private String imgPath;

    @TableField("startTime")
    private LocalDateTime startTime;

    @TableField("endTime")
    private LocalDateTime endTime;

    @TableField("hasDelete")
    private Integer hasDelete;

    @TableField("teacherID")
    private String teacherID;

    @TableField(exist = false)
    private List<User> userList;

    @TableField(exist = false)
    private List<Program> programList;

    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
    public Integer getHasDelete() {
        return hasDelete;
    }

    public void setHasDelete(Integer hasDelete) {
        this.hasDelete = hasDelete;
    }
    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    @Override
    public String toString() {
        return "Course{" +
            "courseID=" + courseID +
            ", courseName=" + courseName +
            ", introduction=" + introduction +
            ", imgPath=" + imgPath +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            ", hasDelete=" + hasDelete +
            ", teacherID=" + teacherID +
        "}";
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Program> getProgramList() {
        return programList;
    }

    public void setProgramList(List<Program> programList) {
        this.programList = programList;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
