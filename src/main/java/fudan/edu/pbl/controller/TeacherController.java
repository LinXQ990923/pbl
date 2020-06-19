package fudan.edu.pbl.controller;

import fudan.edu.pbl.entity.User;
import fudan.edu.pbl.request.LoginAsTeacherRequest;
import fudan.edu.pbl.response.ResultResponse;
import fudan.edu.pbl.response.TeacherResponse;
import fudan.edu.pbl.service.impl.FileServiceImpl;
import fudan.edu.pbl.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class TeacherController {

    @RequestMapping(value = "/teacher/login", method = RequestMethod.POST)
    public ResultResponse teacherLogin(@RequestBody LoginAsTeacherRequest request, HttpSession session){
        UserServiceImpl teacherloginService = new UserServiceImpl();
        User teacher = teacherloginService.getById(request.getId());
        if(teacher != null){
            if(teacher.getPassword().equals(request.getPassword())){
                session.setAttribute("id",request.getId());
                session.setAttribute("password",request.getPassword());
                return new ResultResponse("true","登录成功");
            }else {
                return new ResultResponse("false","密码或用户名错误");
            }
        }else {
            return new ResultResponse("false","用户名不存在");
        }
    }

    @RequestMapping(value = "/teacher/info", method = RequestMethod.GET)
    public TeacherResponse getInfo(HttpSession session){
        UserServiceImpl userService = new UserServiceImpl();
        FileServiceImpl fileService = new FileServiceImpl();
        User user = userService.getById(session.getAttribute("id").toString());
        return new TeacherResponse(user.getUserID(), user.getUserName(), user.getSchool(), user.getDepartment(),
                user.getEmail(), user.getPhone(), fileService.getById(user.getImgID()).getFilePath());
    }

    @RequestMapping(value = "/teacher/loginout", method = RequestMethod.GET)
    public ResultResponse loginout(){
        return null;
    }

    @RequestMapping(value = "/teacher/info/update", method = RequestMethod.POST)
    public ResultResponse updateInfo(){
        return null;
    }

    @RequestMapping(value = "/teacher/password/update", method = RequestMethod.POST)
    public ResultResponse updateRassword(){
        return null;
    }

}
