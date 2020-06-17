package fudan.edu.pbl.request;

public class AddScoreRequest {

    private String score;
    private String description;

    public AddScoreRequest(String score, String description) {
        this.score = score;
        this.description = description;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
