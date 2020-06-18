package fudan.edu.pbl.controller;


import fudan.edu.pbl.response.CommentResponse;
import fudan.edu.pbl.response.ResultResponse;
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
 * @since 2020-06-12
 */

@RestController
public class CommentController {

    @RequestMapping(value = "/course/project/comment", method = RequestMethod.GET)
    public List<CommentResponse> getComments(){
        return null;
    }

    @RequestMapping(value = "/course/project/comment/add", method = RequestMethod.POST)
    public ResultResponse addComment(){
        return null;
    }
}
