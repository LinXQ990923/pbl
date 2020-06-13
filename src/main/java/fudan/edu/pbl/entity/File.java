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
 * @since 2020-06-12
 */
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "fileID", type = IdType.AUTO)
    private Integer fileID;

    @TableField("fileName")
    private String fileName;

    @TableField("filePath")
    private String filePath;

    @TableField("uploadTime")
    private LocalDateTime uploadTime;

    @TableField("uploadUserID")
    private String uploadUserID;

    @TableField("programID")
    private Integer programID;

    public Integer getFileID() {
        return fileID;
    }

    public void setFileID(Integer fileID) {
        this.fileID = fileID;
    }
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }
    public String getUploadUserID() {
        return uploadUserID;
    }

    public void setUploadUserID(String uploadUserID) {
        this.uploadUserID = uploadUserID;
    }
    public Integer getProgramID() {
        return programID;
    }

    public void setProgramID(Integer programID) {
        this.programID = programID;
    }

    @Override
    public String toString() {
        return "File{" +
            "fileID=" + fileID +
            ", fileName=" + fileName +
            ", filePath=" + filePath +
            ", uploadTime=" + uploadTime +
            ", uploadUserID=" + uploadUserID +
            ", programID=" + programID +
        "}";
    }
}
