package fudan.edu.pbl.controller;


import fudan.edu.pbl.response.CourseDetailResponse;
import fudan.edu.pbl.response.CourseResponse;
import fudan.edu.pbl.response.ResultResponse;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

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
public class CourseController {

    @RequestMapping(value = "/user/courses", method = RequestMethod.GET)
    public List<CourseResponse> getAllCourses(){
        return null;
    }

    @RequestMapping(value = "/user/mycourses", method = RequestMethod.GET)
    public List<CourseResponse> getMyCourses(){
        return null;
    }

    @RequestMapping(value = "/course/detail", method = RequestMethod.GET)
    public List<CourseDetailResponse> getCourseDetail(){
        return null;
    }

    @RequestMapping(value = "/course/add", method = RequestMethod.GET)
    public ResultResponse addCourse(){
        return null;
    }

    @RequestMapping(value = "/course/create", method = RequestMethod.GET)
    public ResultResponse createCourse(){
        return null;
    }

    @RequestMapping(value = "/course/delete", method = RequestMethod.GET)
    public ResultResponse deleteCourse(){
        return null;
    }

}
