package fudan.edu.pbl.controller;


import fudan.edu.pbl.response.CourseDetailResponse;
import fudan.edu.pbl.response.ResultResponse;
import fudan.edu.pbl.response.StudentResponse;
import fudan.edu.pbl.response.TeacherResponse;
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
public class AdminController {

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public ResultResponse adminLogin(){
        return null;
    }

    @RequestMapping(value = "/admin/students", method = RequestMethod.GET)
    public List<StudentResponse> getAllStudent(){
        return null;
    }

    @RequestMapping(value = "/admin/student/delete", method = RequestMethod.GET)
    public ResultResponse deleteStudent(){
        return null;
    }

    @RequestMapping(value = "/admin/teachers", method = RequestMethod.GET)
    public List<TeacherResponse> getAllTeacher(){
        return null;
    }

    @RequestMapping(value = "/admin/teacher/delete", method = RequestMethod.GET)
    public ResultResponse deleteTeacher(){
        return null;
    }

    @RequestMapping(value = "/admin/course/create", method = RequestMethod.GET)
    public ResultResponse createCourse(){
        return null;
    }

    @RequestMapping(value = "/admin/courses", method = RequestMethod.GET)
    public List<CourseDetailResponse> getAllCourses(){
        return null;
    }

}
