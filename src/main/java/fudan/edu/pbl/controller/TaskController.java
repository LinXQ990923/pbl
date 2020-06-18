package fudan.edu.pbl.controller;


import fudan.edu.pbl.response.ResultResponse;
import fudan.edu.pbl.response.TaskFinishResponse;
import fudan.edu.pbl.response.TaskResponse;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/course/project/task/create", method = RequestMethod.POST)
    public ResultResponse createTask(){
        return null;
    }

    @RequestMapping(value = "/course/project/task/finished", method = RequestMethod.GET)
    public List<TaskFinishResponse> getTaskFinish(){
        return null;
    }

    @RequestMapping(value = "/course/project/task", method = RequestMethod.GET)
    public List<TaskResponse> getOwnTask(){
        return null;
    }

    @RequestMapping(value = "/course/project/task/finish", method = RequestMethod.GET)
    public ResultResponse finishTask(){
        return null;
    }

}
