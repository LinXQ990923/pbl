package fudan.edu.pbl.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("userID")
    private String userID;

    @TableField("userName")
    private String userName;

    private String password;

    private String email;

    private String phone;

    private String school;

    private String department;

    @TableField("imgPath")
    private String imgPath;

    private Integer role;

    @TableField(exist = false)
    private List<Course> courseList;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
            "userID=" + userID +
            ", userName=" + userName +
            ", password=" + password +
            ", email=" + email +
            ", phone=" + phone +
            ", school=" + school +
            ", department=" + department +
            ", imgPath=" + imgPath +
            ", role=" + role +
        "}";
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
