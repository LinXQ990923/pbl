package fudan.edu.pbl.controller;

import fudan.edu.pbl.entity.User;
import fudan.edu.pbl.request.LoginAsTeacherRequest;
import fudan.edu.pbl.request.UpdateInfoRequest;
import fudan.edu.pbl.request.UpdatePasswordRequest;
import fudan.edu.pbl.response.ResultResponse;
import fudan.edu.pbl.response.TeacherResponse;
import fudan.edu.pbl.service.impl.FileServiceImpl;
import fudan.edu.pbl.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class TeacherController {
    @Autowired UserServiceImpl teacherService;

    @RequestMapping(value = "/teacher/login", method = RequestMethod.POST)
    public ResultResponse teacherLogin(@RequestBody LoginAsTeacherRequest request, HttpSession session) {
        User teacher = teacherService.getById(request.getId());
        if (teacher != null) {
            if (teacher.getRole() == 0) {
                if (teacher.getPassword().equals(request.getPassword())) {
                    session.setAttribute("id", request.getId());
                    session.setAttribute("password", request.getPassword());
                    return new ResultResponse("true", "登录成功");
                } else {
                    return new ResultResponse("false", "密码或用户名错误");
                }
            } else {
                return new ResultResponse("false", "用户名不存在");
            }
        } else {
            return new ResultResponse("false","该用户不是老师！");

        }
    }
    @RequestMapping(value = "/teacher/info", method = RequestMethod.GET)
    public TeacherResponse getInfo(HttpSession session){
        FileServiceImpl fileService = new FileServiceImpl();
        User user = teacherService.getById(session.getAttribute("id").toString());
        return new TeacherResponse(user.getUserID(), user.getUserName(), user.getSchool(), user.getDepartment(),
                user.getEmail(), user.getPhone(),user.getImgPath());
    }

    @RequestMapping(value = "/teacher/loginout", method = RequestMethod.GET)
    public ResultResponse loginout(HttpSession session){
        session.setAttribute("id",null);
        return new ResultResponse("true","登出成功！");
    }

    @RequestMapping(value = "/teacher/info/update", method = RequestMethod.POST)
    public ResultResponse updateInfo(@RequestBody UpdateInfoRequest request,HttpSession session){
        User user = new User();
        user.setUserID(session.getAttribute("id").toString());
        user.setUserName(request.getName());
        user.setSchool(request.getSchool());
        user.setImgPath(request.getImage());
        user.setDepartment(request.getDepartment());
        user.setEmail(request.getEmail());
        if(teacherService.updateById(user)){
            return new ResultResponse("true","信息更新成功！");
        }else {
            return new ResultResponse("false","信息更新失败！");
        }
    }

    @RequestMapping(value = "/teacher/password/update", method = RequestMethod.POST)
    public ResultResponse updateRassword(@RequestBody UpdatePasswordRequest request, HttpSession session){
        User user = new User();
        user.setUserID(session.getAttribute("id").toString());
        user.setPassword(request.getNewPassword());
        if (teacherService.updateById(user)){
            return new ResultResponse("true","密码更新成功！");
        }else {
            return new ResultResponse("false","密码更新失败！");
        }

    }

}
