package fudan.edu.pbl.request;

public class UpdateInfoRequest {

    private String name;
    private String school;
    private String department;
    private String email;
    private String image;

    public UpdateInfoRequest(String name, String school, String department, String email, String image) {
        this.name = name;
        this.school = school;
        this.department = department;
        this.email = email;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
