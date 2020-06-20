package fudan.edu.pbl.request;

public class TeacherAddScoreRequest {

    private String score;
    private String description;
    private String student_id;

    public TeacherAddScoreRequest() {
    }

    public TeacherAddScoreRequest(String score, String description, String student_id) {
        this.score = score;
        this.description = description;
        this.student_id = student_id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }
}
