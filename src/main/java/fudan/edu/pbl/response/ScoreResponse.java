package fudan.edu.pbl.response;

public class ScoreResponse {

    private String name;//项目名称
    private String role;//评分人，1-老师，2-学生
    private String grade;//分数
    private String message;//评价

    public ScoreResponse(String name, String role, String grade, String message) {
        this.name = name;
        this.role = role;
        this.grade = grade;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
