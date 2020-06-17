package fudan.edu.pbl.request;

public class GetStudentTaskRequest {

    private String project_id;
    private String student_id;

    public GetStudentTaskRequest(String project_id, String student_id) {
        this.project_id = project_id;
        this.student_id = student_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }
}
