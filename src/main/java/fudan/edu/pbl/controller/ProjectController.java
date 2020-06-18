package fudan.edu.pbl.controller;

import fudan.edu.pbl.response.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectController {

    @RequestMapping(value = "/course/projects", method = RequestMethod.GET)
    public List<ProjectsResponse> getProjectList(){
        return null;
    }

    @RequestMapping(value = "/course/project/add", method = RequestMethod.GET)
    public ResultResponse addProject(){
        return null;
    }

    @RequestMapping(value = "/course/project/Detail", method = RequestMethod.GET)
    public ProjectsResponse getProject(){
        return null;
    }

    @RequestMapping(value = "/course/project/members", method = RequestMethod.GET)
    public List<MemberResponse> getMembers(){
        return null;
    }

    @RequestMapping(value = "/course/project/file", method = RequestMethod.GET)
    public List<FileResponse> getFiles(){
        return null;
    }

    @RequestMapping(value = "/course/project/delete", method = RequestMethod.GET)
    public ResultResponse deleteProject(){
        return null;
    }

    @RequestMapping(value = "/course/project/create", method = RequestMethod.POST)
    public ResultResponse createProject(){
        return null;
    }

    @RequestMapping(value = "/course/project/student/comment", method = RequestMethod.POST)
    public List<CommentResponse> getStudentComments(){
        return null;
    }

    @RequestMapping(value = "/course/project/student/task", method = RequestMethod.POST)
    public List<TaskResponse> getStudentTask(){
        return null;
    }

    @RequestMapping(value = "/course/project/student/score", method = RequestMethod.POST)
    public List<ScoreResponse> getStudentScore(){
        return null;
    }

}
