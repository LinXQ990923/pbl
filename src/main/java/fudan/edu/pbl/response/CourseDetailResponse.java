package fudan.edu.pbl.response;

public class CourseDetailResponse {

    private String id;
    private String name;
    private String introduction;
    private String teacher;
    private String end_time;
    private String image;
    private String isAdd;

    public CourseDetailResponse(String id, String name, String introduction, String teacher, String end_time, String image, String isAdd) {
        this.id = id;
        this.name = name;
        this.introduction = introduction;
        this.teacher = teacher;
        this.end_time = end_time;
        this.image = image;
        this.isAdd = isAdd;
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIsAdd() {
        return isAdd;
    }

    public void setIsAdd(String isAdd) {
        this.isAdd = isAdd;
    }
}
