package fudan.edu.pbl.response;

public class TaskResponse {

    private String id;//任务id
    private String name;//任务名称
    private String description;//任务要求描述
    private String start_time;//任务开始时间
    private String end_time;//任务结束时间
    private String finish;//任务是否完成，默认为false

    public TaskResponse(String id, String name, String description, String start_time, String end_time, String finish) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.start_time = start_time;
        this.end_time = end_time;
        this.finish = finish;
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

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }
}
