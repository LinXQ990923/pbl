package fudan.edu.pbl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class Discussion implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "discussionID", type = IdType.AUTO)
    private Integer discussionID;

    @TableField("programID")
    private Integer programID;

    @TableField(exist = false)
    private List<Comment> commentList;

    public Integer getDiscussionID() {
        return discussionID;
    }

    public void setDiscussionID(Integer discussionID) {
        this.discussionID = discussionID;
    }
    public Integer getProgramID() {
        return programID;
    }

    public void setProgramID(Integer programID) {
        this.programID = programID;
    }

    @Override
    public String toString() {
        return "Discussion{" +
            "discussionID=" + discussionID +
            ", programID=" + programID +
        "}";
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
