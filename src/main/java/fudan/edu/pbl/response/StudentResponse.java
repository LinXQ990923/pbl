package fudan.edu.pbl.response;

public class StudentResponse {

    public String id;
    public String name;
    public String school;
    public String department;
    public String email;
    public String phone;
    public String image;

    public StudentResponse(){

    }

    public StudentResponse(String id, String name, String school, String department, String email, String phone, String image) {
        this.id = id;
        this.name = name;
        this.school = school;
        this.department = department;
        this.email = email;
        this.phone = phone;
        this.image = image;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
