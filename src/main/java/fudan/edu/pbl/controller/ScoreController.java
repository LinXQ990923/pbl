package fudan.edu.pbl.controller;

import fudan.edu.pbl.entity.Comment;
import fudan.edu.pbl.entity.Course;
import fudan.edu.pbl.entity.Program;
import fudan.edu.pbl.entity.Task;
import fudan.edu.pbl.request.TeacherAddScoreRequest;
import fudan.edu.pbl.response.CommentResponse;
import fudan.edu.pbl.response.ResultResponse;
import fudan.edu.pbl.response.ScoreResponse;
import fudan.edu.pbl.response.TaskResponse;
import fudan.edu.pbl.service.CommentService;
import fudan.edu.pbl.service.ProgramService;
import fudan.edu.pbl.service.impl.CommentServiceImpl;
import fudan.edu.pbl.service.impl.CourseServiceImpl;
import fudan.edu.pbl.service.impl.ProgramServiceImpl;
import fudan.edu.pbl.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class ScoreController {

    @Autowired UserServiceImpl userService;
    @Autowired ProgramServiceImpl programService;
    @Autowired CommentServiceImpl commentService;
    @Autowired CourseServiceImpl courseService;

    @RequestMapping(value = "/course/project/score", method = RequestMethod.GET)
    public List<ScoreResponse> getScore(@RequestParam(value = "project_id", required = true)String id, HttpSession session){
        List<ScoreResponse> scoreList = new ArrayList<>();
        Program program = programService.getById(id);
        ScoreResponse teacherScore = new ScoreResponse();
        ScoreResponse studentScore = new ScoreResponse();
        teacherScore.setName(program.getProgramName());
        teacherScore.setRole("1");
        teacherScore.setGrade(String.valueOf(userService.getTeacherGrade(Integer.parseInt(id), session.getAttribute("id").toString())*Integer.parseInt(program.getGradePolicy())*0.01));
        teacherScore.setMessage("");
        scoreList.add(teacherScore);
        studentScore.setName(program.getProgramName());
        studentScore.setRole("2");
        studentScore.setGrade(String.valueOf(userService.getStudentGrade(Integer.parseInt(id), session.getAttribute("id").toString())*(100-Integer.parseInt(program.getGradePolicy()))*0.01));
        studentScore.setMessage("");
        scoreList.add(studentScore);
        return scoreList;
    }

    @RequestMapping(value = "/course/project/score/comment", method = RequestMethod.GET)
    public List<CommentResponse> getScoreComments(@RequestParam(value = "project_id", required = true)String id, HttpSession session){
        String peerID = getPeerID(session.getAttribute("id").toString(), id);
        List<Comment> commentList = commentService.getCommentsByStudent(peerID);
        List<CommentResponse> commentsList = new ArrayList<>();
        for(int i = 0; i < commentList.size(); i++){
            Comment comment = commentList.get(i);
            CommentResponse comments = new CommentResponse();
            if(comment.getDiscussionID().toString().equals(id)){
                comments.setId(comment.getUserID());
                comments.setAuthor(userService.getById(comment.getUserID()).getUserName());
                comments.setDate(comment.getPublishTime().toString());
                if(comment.getReplyID() != null){
                    comments.setReply(comment.getReplyID().toString());
                }else{
                    comments.setReply("");
                }
                comments.setContent(comment.getContent());
                commentsList.add(comments);
            }
        }
        return commentsList;
    }

    @RequestMapping(value = "/course/project/score/task", method = RequestMethod.GET)
    public List<TaskResponse> getScoreTask(@RequestParam(value = "project_id", required = true)String id, HttpSession session){
        String peerID = getPeerID(session.getAttribute("id").toString(), id);
        List<Task> taskList = programService.getProgramByIdWithTask(Integer.parseInt(id)).getTaskList();
        List<TaskResponse> tasksList = new ArrayList<>();
        List<HashMap> objectList;
        HashMap object;
        for(int i = 0; i < taskList.size(); i++){
            Task task = taskList.get(i);
            TaskResponse tasksTask = new TaskResponse();
            objectList = userService.selectFromTaskUser(session.getAttribute("id").toString());
            for(int j = 0; j < objectList.size(); j++){
                object = objectList.get(j);
                if(task.getTaskID().toString().equals(object.get("taskID").toString())){
                    tasksTask.setId(task.getTaskID().toString());
                    tasksTask.setName(task.getTaskName());
                    tasksTask.setDescription(task.getIntroduction());
                    tasksTask.setStart_time(task.getStartTime().toString());
                    tasksTask.setEnd_time(task.getEndTime().toString());
                    tasksTask.setFinish(object.get("isFinish").toString());
                    tasksList.add(tasksTask);
                    break;
                }
            }
        }
        return tasksList;
    }

    @RequestMapping(value = "/course/project/score/add", method = RequestMethod.POST)
    public ResultResponse addScore(@RequestBody TeacherAddScoreRequest request, HttpSession session){
        String userID1 = session.getAttribute("id").toString();
        String userID2 = request.getStudent_id();
        if(userService.checkIfGraded(Integer.parseInt(session.getAttribute("program_id").toString()), userID1, userID2) != null){
            return new ResultResponse("false", "Grade exist.");
        }else{
            userService.grade(Integer.parseInt(session.getAttribute("program_id").toString()), userID1, userID2, 2, Double.parseDouble(request.getScore()), request.getDescription());
            return new ResultResponse("true", "Add grade successfully!");
        }
    }

    public String getPeerID(String userId, String projectId){
        List<HashMap> list = userService.selectFromProgramUser(projectId);
        HashMap<String, Object> hashMap;
        int length = list.size();
        for(int i = 0; i <list.size(); i++){
            hashMap = list.get(i);
            if(userId.equals(hashMap.get("userID").toString())){
                if(i != list.size()-1){
                    return list.get(i+1).get("userID").toString();
                }
                    return list.get(0).get("userID").toString();
            }
        }
        return null;
    }

}
