package fudan.edu.pbl.controller;

import fudan.edu.pbl.entity.User;
import fudan.edu.pbl.request.LoginAsStudentRequest;
import fudan.edu.pbl.request.SignUpAsStudentRequest;
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
            if(student.getPassword().equals(request.getPassword())){
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
            if (studentService.save(user)){
                session.setAttribute("id",request.getId());
                return new ResultResponse("true","注册成功");
            }else {
                return new ResultResponse("false","数据库出错");
            }
        }
    }

    @RequestMapping(value = "/student/info", method = RequestMethod.GET)
    public StudentResponse getInfo(){
        return null;
    }

    @RequestMapping(value = "/student/loginout", method = RequestMethod.GET)
    public ResultResponse loginout(){
        return null;
    }

    @RequestMapping(value = "/student/info/update", method = RequestMethod.POST)
    public ResultResponse updateInfo(){
        return null;
    }

    @RequestMapping(value = "/student/password/update", method = RequestMethod.POST)
    public ResultResponse updatePassword(){
        return null;
    }

}
