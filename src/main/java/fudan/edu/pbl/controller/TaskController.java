package fudan.edu.pbl.controller;


import fudan.edu.pbl.entity.Task;
import fudan.edu.pbl.request.CreateTaskRequest;
import fudan.edu.pbl.response.ResultResponse;
import fudan.edu.pbl.response.TaskFinishResponse;
import fudan.edu.pbl.response.TaskResponse;
import fudan.edu.pbl.service.TaskService;
import fudan.edu.pbl.service.UserService;
import fudan.edu.pbl.service.impl.ProgramServiceImpl;
import fudan.edu.pbl.service.impl.TaskServiceImpl;
import fudan.edu.pbl.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lwy
 * @since 2020-06-13
 */

@RestController
public class TaskController {

    @Autowired TaskServiceImpl taskService;
    @Autowired ProgramServiceImpl programService;
    @Autowired UserServiceImpl userService;

    @RequestMapping(value = "/course/project/task/create", method = RequestMethod.POST)
    public ResultResponse createTask(@RequestBody CreateTaskRequest request, HttpSession session){
        Task task = new Task();
        task.setTaskName(request.getName());
        task.setIntroduction(request.getDescription());
        task.setStartTime(LocalDateTime.parse(request.getStart_time()));
        task.setEndTime(LocalDateTime.parse(request.getEnd_time()));
        task.setProgramID(Integer.parseInt(session.getAttribute("program_id").toString()));
        task.setHasDelete(0);
        Boolean flag = taskService.save(task);
        if(flag){
            return new ResultResponse("true", "Create task successfully!");
        }else{
            return new ResultResponse("false", "Create task failed!");
        }
    }

    @RequestMapping(value = "/course/project/task/finished", method = RequestMethod.GET)
    public List<TaskFinishResponse> getTaskFinish(@RequestParam (value = "project_id", required = true)String id){
        List<Task> taskList = programService.getProgramByIdWithTask(Integer.parseInt(id)).getTaskList();
        List<TaskFinishResponse> taskFinishList = new ArrayList<>();
        List<HashMap> objectList;
        HashMap object;
        List<String> finished = new ArrayList<>();
        List<String> not_finished = new ArrayList<>();
        for(int i = 0; i < taskList.size(); i++){
            Task task = taskList.get(i);
            TaskFinishResponse taskFinish = new TaskFinishResponse();
            taskFinish.setId(task.getTaskID().toString());
            taskFinish.setName(task.getTaskName());
            objectList = userService.findFishedTask(task.getTaskID());
            for(int j = 0; j < objectList.size(); j++){
                object = objectList.get(j);
                finished.add(object.get("userID").toString());
            }
            taskFinish.setFinished(finished);
            objectList = userService.findUnfishedTask(task.getTaskID());
            for(int k = 0; k < objectList.size(); k++){
                object = objectList.get(k);
                not_finished.add(object.get("userID").toString());
            }
            taskFinish.setNot_finished(not_finished);
            taskFinishList.add(taskFinish);
        }
        return taskFinishList;
    }

    @RequestMapping(value = "/course/project/task", method = RequestMethod.GET)
    public List<TaskResponse> getOwnTask(@RequestParam(value = "project_id")String id, HttpSession session){
        List<Task> taskList = programService.getProgramByIdWithTask(Integer.parseInt(id)).getTaskList();
        List<TaskResponse> ownTaskList = new ArrayList<>();
        List<HashMap> objectList;
        HashMap object;
        for(int i = 0; i < taskList.size(); i++){
            Task task = taskList.get(i);
            TaskResponse ownTask = new TaskResponse();
            objectList = userService.selectFromTaskUser(session.getAttribute("id").toString());
            for(int j = 0; j < objectList.size(); j++){
                object = objectList.get(j);
                if(task.getTaskID().toString().equals(object.get("taskID").toString())){
                    ownTask.setId(task.getTaskID().toString());
                    ownTask.setName(task.getTaskName());
                    ownTask.setDescription(task.getIntroduction());
                    ownTask.setStart_time(task.getStartTime().toString());
                    ownTask.setEnd_time(task.getEndTime().toString());
                    ownTask.setFinish(object.get("isFinish").toString());
                    ownTaskList.add(ownTask);
                    break;
                }
            }
        }
        return ownTaskList;
    }

    @RequestMapping(value = "/course/project/task/finish", method = RequestMethod.GET)
    public ResultResponse finishTask(@RequestParam(value = "task_id")String id, HttpSession session){
        userService.finishTask(Integer.parseInt(id), session.getAttribute("id").toString());
        return new ResultResponse("true", "Finish task.");
    }

}
