package fudan.edu.pbl.request;

public class AddCommentRequest {

    private String author_id;
    private String author_name;
    private String date;
    private String reply;
    private String content;
    private String project_id;

    public AddCommentRequest(String author_id, String author_name, String date, String reply, String content, String project_id) {
        this.author_id = author_id;
        this.author_name = author_name;
        this.date = date;
        this.reply = reply;
        this.content = content;
        this.project_id = project_id;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
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

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }
}
