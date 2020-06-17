package fudan.edu.pbl.response;

public class CommentResponse {

    private String id;//评论id
    private String author;//评论发布人姓名
    private String date;//评论发布时间 例如 2020-06-11
    private String reply;//回复的评论id，如果为第一条发布的，该项为空string“”
    private String content;//评论内容

    public CommentResponse(String id, String author, String date, String reply, String content) {
        this.id = id;
        this.author = author;
        this.date = date;
        this.reply = reply;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
