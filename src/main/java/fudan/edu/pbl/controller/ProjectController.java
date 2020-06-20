package fudan.edu.pbl.controller;

import fudan.edu.pbl.entity.*;
import fudan.edu.pbl.request.CreateProjectRequest;
import fudan.edu.pbl.request.GetStudentCommentsRequest;
import fudan.edu.pbl.request.GetStudentTaskRequest;
import fudan.edu.pbl.response.*;
import fudan.edu.pbl.service.UserService;
import fudan.edu.pbl.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class ProjectController {
    @Autowired ProgramServiceImpl programService;
    @Autowired UserService userService;
    @Autowired CourseServiceImpl courseService;
    @Autowired DiscussionServiceImpl discussionService;
    @Autowired CommentServiceImpl commentService;
    @Autowired TaskServiceImpl taskService;
    @Autowired FileServiceImpl fileService;



    @RequestMapping(value = "/course/projects", method = RequestMethod.GET)
    public List<ProjectsResponse> getProjectList(@RequestParam(value = "course_id",required = true)String id, HttpSession session) {

        List<ProjectsResponse> projectsResponses = new ArrayList<>();
        ProjectsResponse projectsDetail = new ProjectsResponse();
        List<Program> programList = programService.list();
        session.setAttribute("program_id", projectsDetail.getId());
        session.setAttribute("course_id",id);
        for (int i = 0; i < programList.size(); i++) {
            Program program = programList.get(i);
            if (program.getCourseID().toString().equals(id)) {
                projectsDetail.setId(program.getProgramID().toString());
                projectsDetail.setDescription(program.getIntroduction());
                projectsDetail.setName(program.getProgramName());
                projectsDetail.setStart_time(program.getStartTime().toString());
                projectsDetail.setEnd_time(program.getEndTime().toString());
                if (userService.ifChooseProgram(program.getProgramID(), session.getAttribute("id").toString()) == null) {
                    projectsDetail.setIsAdd("false");
                } else {
                    projectsDetail.setIsAdd("true");
                }
                projectsResponses.add(projectsDetail);
            }
        }
        return projectsResponses;
    }

    @RequestMapping(value = "/course/project/add", method = RequestMethod.GET)
    public ResultResponse addProject(@RequestParam(value = "project_id",required = true)String id,HttpSession session){
        Program program = new Program();
        List<HashMap> test = userService.selectFromProgramUser(id);
        int islead = (test==null || test.size() == 0)?1:2;
        userService.chooseProgram(program.getProgramID(),session.getAttribute("id").toString(),0);
        return new ResultResponse("true","课程添加成功！");
    }

    @RequestMapping(value = "/course/project/Detail", method = RequestMethod.GET)
    public ProjectsResponse getProject(@RequestParam(value = "project_id",required = true)String id ,HttpSession session) {
        Program program = programService.getById(id);
        if (program != null) {
            String isAdd = userService.ifChooseProgram(program.getProgramID(), session.getAttribute("id").toString()) == null ? "false" : "true";
            return new ProjectsResponse(program.getProgramID().toString(),
                    program.getStartTime().toString(), program.getEndTime().toString(), program.getProgramName(), program.getIntroduction(),isAdd, null, null);
        }else {
            return null;
        }
    }


    @RequestMapping(value = "/course/project/members", method = RequestMethod.GET)
    public List<MemberResponse> getMembers(@RequestParam(value = "project_id",required = true)String id,HttpSession session){
        List<MemberResponse> memberResponses = new ArrayList<>();
        MemberResponse memberlist = new MemberResponse();
        List<Program> program = programService.list();
        List<User> users = userService.list();
        Program programs = programService.getById(id);

        for (int i =0;i<users.size(); i++){
            User memeber = users.get(i);
            if(userService.ifChooseProgram(programs.getProgramID(),session.getAttribute("id").toString()) ==null){
                memberlist.setId(memeber.getUserID());
                memberlist.setName(memeber.getUserName());
                memberResponses.add(memberlist);
            }
        }return memberResponses;
    }

    @RequestMapping(value = "/course/project/file", method = RequestMethod.GET)
    public List<FileResponse> getFiles(@RequestParam(value = "project_id",required = true)String id,HttpSession session){
        List<FileResponse> fileResponses = new ArrayList<>();
        FileResponse fileResponse = new FileResponse();

        Program program = programService.getById(id);
        List<File> files = fileService.list();

        for (int i = 0;i<files.size();i++){
            File file = files.get(i);
            if(file.getProgramID().equals(program.getProgramID())){
                fileResponse.setId(file.getFileID().toString());
                fileResponse.setName(file.getFileName());
                fileResponse.setUploader(file.getUploadUserID());
                fileResponse.setDate(file.getUploadTime().toString());
                fileResponse.setPath(file.getFilePath());
                if(file.getFileID() == null){
                    fileResponse.setDelete("false");
                }else {
                    fileResponse.setDelete("true");
                }
                fileResponses.add(fileResponse);
            }
        }return fileResponses;

    }

    @RequestMapping(value = "/course/project/delete", method = RequestMethod.GET)
    public ResultResponse deleteProject(@RequestParam(value = "project_id",required = true)String id ){
        Boolean delete = programService.removeById(id);
        if (delete){
            return new ResultResponse("true","成功删除项目！");
        }else {
            return new ResultResponse("false","删除项目失败！");
        }
    }

    @RequestMapping(value = "/course/project/create", method = RequestMethod.POST)
    public ResultResponse createProject(@RequestBody CreateProjectRequest request, HttpSession session){
        session.getAttribute("course_id");
        Program program = new Program();
        program.setCourseID(new Integer(session.getAttribute("course_id").toString()));
        program.setProgramName(request.getName());
        program.setIntroduction(request.getDescription());
        program.setStartTime(LocalDateTime.parse(request.getStart_time()));
        program.setEndTime(LocalDateTime.parse(request.getEnd_time()));
        program.setGradePolicy(request.getTeacherProportion());
        if(programService.save(program)){
            return new ResultResponse("true","成功创建项目！");
        }else {
            return new ResultResponse("false","创建项目失败！");
        }
    }

    @RequestMapping(value = "/course/project/student/comment", method = RequestMethod.POST)
    public List<CommentResponse> getStudentComments(@RequestBody GetStudentCommentsRequest request,HttpSession session){
        //   Discussion discussion = discussionService.getDiscussionByIdWithComment(Integer.parseInt(id))
        List<CommentResponse> commentResponses = new ArrayList<>();
        CommentResponse commentResponse = new CommentResponse();
        //List<Program> programList = programService.list();
        //List<Discussion> discussions = discussionService.list();
        List<Comment> comments = commentService.list();

        if (session.getAttribute("program_id").toString() != null){
            //   if (request.getStudent_id() == session.getAttribute("id")) {
            for (int i = 0; i < comments.size(); i++) {
                Comment comment = comments.get(i);
                if (request.getStudent_id().equals(comment.getUserID())) {
                    commentResponse.setId(comment.getCommentID().toString());
                    commentResponse.setAuthor(userService.getById(comment.getUserID()).getUserName());
                    commentResponse.setDate(comment.getPublishTime().toString());
                    if (comment.getReplyID() != null) {
                        comment.setReplyID(comment.getReplyID());
                    } else {
                        comment.setReplyID(null);
                    }
                    comment.setContent(comment.getContent());
                    commentResponses.add(commentResponse);
                }
            }

        }return commentResponses;
    }

    @RequestMapping(value = "/course/project/student/task", method = RequestMethod.POST)
    public List<TaskResponse> getStudentTask(@RequestBody GetStudentTaskRequest request,HttpSession session){
        List<TaskResponse> taskResponses = new ArrayList<>();
        TaskResponse taskResponse = new TaskResponse();
        //List<Program> program =  programService.list();
        List<Task> tasks = taskService.list();
        List<HashMap> objectList;
        HashMap object;
        if(session.getAttribute("program_id").toString() != null){
            for (int i = 0;i<tasks.size();i++){
                Task task = tasks.get(i);
                objectList = userService.selectFromTaskUser(session.getAttribute("id").toString());
                for(int j = 0; j < objectList.size(); j++){
                    object = objectList.get(j);
                    if (request.getStudent_id() == session.getAttribute("id")){
                        taskResponse.setId(task.getTaskID().toString());
                        taskResponse.setName(task.getTaskName());
                        taskResponse.setDescription(task.getIntroduction());
                        taskResponse.setStart_time(task.getStartTime().toString());
                        taskResponse.setEnd_time(task.getEndTime().toString());
                        taskResponse.setFinish(object.get("isFinish").toString());
                        taskResponses.add(taskResponse);
                        break;
                    }
                }
            }
        }return taskResponses;
    }

    @RequestMapping(value = "/course/project/student/score", method = RequestMethod.POST)
    public List<ScoreResponse> getStudentScore(HttpSession session ){
        List<ScoreResponse> scoreResponses = new ArrayList<>();
        //ScoreResponse scoreResponse = new ScoreResponse();
        ScoreResponse teacherScore = new ScoreResponse();
        ScoreResponse studentScore = new ScoreResponse();
        //List<User> user = userService.list();
        List<Program> programs = programService.list();
        for (int i =0;i<programs.size();i++){
            Program program = programs.get(i);
            if(userService.ifChooseProgram(program.getProgramID(),session.getAttribute("id").toString()) !=null){
                teacherScore.setName(program.getProgramName());
                teacherScore.setRole("1");
                teacherScore.setGrade(String.valueOf(userService.getTeacherGrade(program.getProgramID(), session.getAttribute("id").toString())*Integer.parseInt(program.getGradePolicy())*0.01));
                teacherScore.setMessage("");
                scoreResponses.add(teacherScore);
                studentScore.setName(program.getProgramName());
                studentScore.setRole("2");
                studentScore.setGrade(String.valueOf(userService.getStudentGrade(program.getProgramID(), session.getAttribute("id").toString())*(100-Integer.parseInt(program.getGradePolicy()))*0.01));
                studentScore.setMessage("");
                scoreResponses.add(studentScore);
                break;
            }

        }return scoreResponses;
    }

}
