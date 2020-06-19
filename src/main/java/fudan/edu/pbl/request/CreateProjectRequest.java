package fudan.edu.pbl.request;

public class CreateProjectRequest {

    private String course_id;
    private String name;
    private String description;
    private String start_time;
    private String end_time;
    private String teacherProportion;
    private String studentProportion;

    public CreateProjectRequest(){

    }

    public CreateProjectRequest(String course_id, String name, String description, String start_time, String end_time, String teacherProportion, String studentProportion) {
        this.course_id = course_id;
        this.name = name;
        this.description = description;
        this.start_time = start_time;
        this.end_time = end_time;
        this.teacherProportion = teacherProportion;
        this.studentProportion = studentProportion;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getTeacherProportion() {
        return teacherProportion;
    }

    public void setTeacherProportion(String teacherProportion) {
        this.teacherProportion = teacherProportion;
    }

    public String getStudentProportion() {
        return studentProportion;
    }

    public void setStudentProportion(String studentProportion) {
        this.studentProportion = studentProportion;
    }
}
