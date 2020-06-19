package fudan.edu.pbl.response;

import java.util.List;

public class TaskFinishResponse {

    private String id;//任务id
    private String name;//任务名
    private List<String> finished;//已完成的组员名字
    private List<String> not_finished;

    public TaskFinishResponse(){

    }

    public TaskFinishResponse(String id, String name, List<String> finished, List<String> not_finished) {
        this.id = id;
        this.name = name;
        this.finished = finished;
        this.not_finished = not_finished;
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

    public List<String> getFinished() {
        return finished;
    }

    public void setFinished(List<String> finished) {
        this.finished = finished;
    }

    public List<String> getNot_finished() {
        return not_finished;
    }

    public void setNot_finished(List<String> not_finished) {
        this.not_finished = not_finished;
    }
}
