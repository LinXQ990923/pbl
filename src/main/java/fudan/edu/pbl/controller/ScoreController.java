package fudan.edu.pbl.controller;

import fudan.edu.pbl.entity.Task;
import fudan.edu.pbl.response.CommentResponse;
import fudan.edu.pbl.response.ResultResponse;
import fudan.edu.pbl.response.ScoreResponse;
import fudan.edu.pbl.response.TaskResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScoreController {

    @RequestMapping(value = "/course/project/score", method = RequestMethod.GET)
    public List<ScoreResponse> getScore(){
        return null;
    }

    @RequestMapping(value = "/course/project/score/comment", method = RequestMethod.POST)
    public List<CommentResponse> getScoreComments(){
        return null;
    }

    @RequestMapping(value = "/course/project/score/task", method = RequestMethod.GET)
    public List<TaskResponse> getScoreTask(){
        return null;
    }

    @RequestMapping(value = "/course/project/score/add", method = RequestMethod.POST)
    public ResultResponse addScore(){
        return null;
    }

}
