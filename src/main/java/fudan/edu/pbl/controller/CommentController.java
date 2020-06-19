package fudan.edu.pbl.controller;


import fudan.edu.pbl.entity.Comment;
import fudan.edu.pbl.entity.Discussion;
import fudan.edu.pbl.request.AddCommentRequest;
import fudan.edu.pbl.response.CommentResponse;
import fudan.edu.pbl.response.ResultResponse;
import fudan.edu.pbl.service.impl.CommentServiceImpl;
import fudan.edu.pbl.service.impl.DiscussionServiceImpl;
import fudan.edu.pbl.service.impl.ProgramServiceImpl;
import fudan.edu.pbl.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Autowired CommentServiceImpl commentService;
    @Autowired ProgramServiceImpl programService;
    @Autowired DiscussionServiceImpl discussionService;
    @Autowired UserServiceImpl userService;


    @RequestMapping(value = "/course/project/comment", method = RequestMethod.GET)
    public List<CommentResponse> getComments(@RequestParam (value = "project_id", required = true) String id, HttpSession session){
        Discussion discussion = discussionService.getDiscussionByIdWithComment(Integer.parseInt(id));
        session.setAttribute("discussion_id", discussion.getDiscussionID());
        List<Comment> commentList = discussion.getCommentList();
        List<CommentResponse> commentsList = new ArrayList<>();
        for(int i = 0; i < commentList.size(); i++){
            Comment comment = commentList.get(i);
            CommentResponse comments = new CommentResponse();
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
        return commentsList;
    }

    @RequestMapping(value = "/course/project/comment/add", method = RequestMethod.POST)
    public ResultResponse addComment(@RequestParam AddCommentRequest request, HttpSession session){
        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setReplyID(Integer.parseInt(request.getReply()));
        comment.setDiscussionID(Integer.parseInt(session.getAttribute("discussion_id").toString()));
        comment.setPublishTime(LocalDate.parse(request.getDate()));
        comment.setUserID(request.getAuthor_id());
        Boolean flag = commentService.save(comment);
        if(flag){
            return new ResultResponse("true", "Add comment successfully!");
        }else{
            return new ResultResponse("false", "add comment failed");
        }

    }
}
