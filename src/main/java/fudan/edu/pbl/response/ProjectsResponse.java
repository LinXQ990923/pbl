package fudan.edu.pbl.response;

public class ProjectsResponse {

    private String id;
    private String name;
    private String description;//项目描述
    private String start_time;//项目开始时间
    private String end_time;//项目结束时间，如2020-06-11
    private String isAdd;//是否加入该项目，true或false
    private String role;//1-组长或2-组员
    private String score;//评分方式：true-有互评  false-无互评

    public ProjectsResponse(){

    }

    public ProjectsResponse(String id, String name, String description, String start_time, String end_time, String isAdd, String role, String score) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.start_time = start_time;
        this.end_time = end_time;
        this.isAdd = isAdd;
        this.role = role;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getIsAdd() {
        return isAdd;
    }

    public void setIsAdd(String isAdd) {
        this.isAdd = isAdd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
