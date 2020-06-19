package fudan.edu.pbl.response;

public class MemberResponse {

    private String id;
    private String name;//组员名字
    private String role;//角色：1-组长 2-组员

    public MemberResponse(){

    }

    public MemberResponse(String id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
