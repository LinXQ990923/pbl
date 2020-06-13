package fudan.edu.pbl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "commentID", type = IdType.AUTO)
    private Integer commentID;

    private String content;

    @TableField("replyID")
    private Integer replyID;

    @TableField("discussionID")
    private Integer discussionID;

    @TableField("userID")
    private String userID;

    @TableField("publishTime")
    private LocalDate publishTime;

    public Integer getCommentID() {
        return commentID;
    }

    public void setCommentID(Integer commentID) {
        this.commentID = commentID;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Integer getReplyID() {
        return replyID;
    }

    public void setReplyID(Integer replyID) {
        this.replyID = replyID;
    }
    public Integer getDiscussionID() {
        return discussionID;
    }

    public void setDiscussionID(Integer discussionID) {
        this.discussionID = discussionID;
    }
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    public LocalDate getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDate publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
            "commentID=" + commentID +
            ", content=" + content +
            ", replyID=" + replyID +
            ", discussionID=" + discussionID +
            ", userID=" + userID +
            ", publishTime=" + publishTime +
        "}";
    }
}
