package fudan.edu.pbl.controller;

import fudan.edu.pbl.entity.User;
import fudan.edu.pbl.request.LoginAsStudentRequest;
import fudan.edu.pbl.request.SignUpAsStudentRequest;
import fudan.edu.pbl.request.UpdateInfoRequest;
import fudan.edu.pbl.request.UpdatePasswordRequest;
import fudan.edu.pbl.response.ResultResponse;
import fudan.edu.pbl.response.StudentResponse;
import fudan.edu.pbl.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class StudentController {
    @Autowired UserServiceImpl studentService;

    @RequestMapping(value = "/student/login", method = RequestMethod.POST)
    public ResultResponse studentLogin(@RequestBody LoginAsStudentRequest request, HttpSession session){
        User student = studentService.getById(request.getId());
        if(student != null){
            if(student.getRole() == 0){
                if(student.getPassword().equals(request.getPassword())){
                    session.setAttribute("id",request.getId());
                    return new ResultResponse("true","登录成功");
                }else {
                    return new ResultResponse("false","密码或用户名错误");
                }
            }else {
                return new ResultResponse("false","该用户不是学生！");
            }
        }else {
            return new ResultResponse("false","用户名不存在");
        }

    }

    @RequestMapping(value = "/student/signup", method = RequestMethod.POST)
    public ResultResponse studentSignup(@RequestBody SignUpAsStudentRequest request, HttpSession session){
        User student = studentService.getById(request.getId());
        if(student != null){
            return new ResultResponse("false","用户名已存在,注册失败");
        }else {
            User user=new User();
            user.setUserID(request.getId());
            user.setPassword(request.getPassword());
            user.setEmail(request.getEmail());
            user.setUserName(request.getName());
            user.setRole(0);
            if (studentService.save(user)){
                session.setAttribute("id",request.getId());
                return new ResultResponse("true","注册成功");
            }else {
                return new ResultResponse("false","数据库出错");
            }
        }
    }

    @RequestMapping(value = "/student/getinfo", method = RequestMethod.GET)
    public StudentResponse getInfo(HttpSession session){

        User student = studentService.getById(session.getAttribute("id").toString());
        return new StudentResponse(student.getUserID(),student.getUserName(),student.getSchool(),student.getDepartment(),student.getEmail(),student.getPhone(),student.getImgPath());
    }

    @RequestMapping(value = "/student/loginout", method = RequestMethod.GET)
    public ResultResponse loginout(HttpSession session){
        session.setAttribute("id",null);
        return new ResultResponse("true","登出成功！");
    }

    @RequestMapping(value = "/student/info/update", method = RequestMethod.POST)
    public ResultResponse updateInfo(@RequestBody UpdateInfoRequest request, HttpSession session){
        User user = studentService.getById(session.getAttribute("id").toString());
        user.setUserName(request.getName());
        user.setDepartment(request.getDepartment());
        user.setEmail(request.getEmail());
        user.setImgPath(request.getImage());
        user.setSchool(request.getSchool());
        if (studentService.updateById(user)){
            return new ResultResponse("true","信息更新成功！");
        }else {
            return new ResultResponse("false", "信息更新失败！");

        }
    }

    @RequestMapping(value = "/student/password/update", method = RequestMethod.POST)
    public ResultResponse updatePassword(@RequestBody UpdatePasswordRequest request, HttpSession session){
        User user = studentService.getById(session.getAttribute("id").toString());
        if (!user.getPassword().equals(request.getOldPassword())){
            return new ResultResponse("false","旧密码错误！");
        }
        user.setPassword(request.getNewPassword());
        if (studentService.updateById(user)){
            return new ResultResponse("true","密码更新成功！");
        }else {
            return new ResultResponse("false","信息更新失败！");
        }
    }

}
