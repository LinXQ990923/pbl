package fudan.edu.pbl.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lwy
 * @since 2020-06-12
 */
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("adminID")
    private String adminID;

    private String password;

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
            "adminID=" + adminID +
            ", password=" + password +
        "}";
    }
}
