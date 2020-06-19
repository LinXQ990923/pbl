package fudan.edu.pbl.controller;


import fudan.edu.pbl.entity.Course;
import fudan.edu.pbl.entity.User;
import fudan.edu.pbl.request.CreateCourseRequest;
import fudan.edu.pbl.response.CourseDetailResponse;
import fudan.edu.pbl.response.CourseResponse;
import fudan.edu.pbl.response.ResultResponse;
import fudan.edu.pbl.service.CourseService;
import fudan.edu.pbl.service.impl.CourseServiceImpl;
import fudan.edu.pbl.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.servlet.http.HttpSession;
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
public class CourseController {

    @Autowired UserServiceImpl userService;
    @Autowired CourseServiceImpl courseService;

    @RequestMapping(value = "/user/courses", method = RequestMethod.GET)
    public List<CourseResponse> getAllCourses(){
        List<Course> courseList = courseService.list();
        List<CourseResponse> coursesList = new ArrayList<>();
        for(int i = 0; i < courseList.size(); i++){
            Course course = courseList.get(i);
            CourseResponse courses = new CourseResponse();
            courses.setId(course.getCourseID().toString());
            courses.setName(course.getCourseName());
            courses.setIntroduction(course.getIntroduction());
            courses.setImage(course.getImgPath());
            coursesList.add(courses);
        }
        return coursesList;
    }

    @RequestMapping(value = "/user/mycourses", method = RequestMethod.GET)
    public List<CourseResponse> getMyCourses(@RequestParam HttpSession session){
        User user = userService.getByIdWithProperties(session.getAttribute("id").toString());
        List<Course> courseList = user.getCourseList();
        List<CourseResponse> coursesList = new ArrayList<>();
        for(int i = 0; i < courseList.size(); i++){
            Course course = courseList.get(i);
            CourseResponse courses = new CourseResponse();
            courses.setId(course.getCourseID().toString());
            courses.setName(course.getCourseName());
            courses.setIntroduction(course.getIntroduction());
            courses.setImage(course.getImgPath());
            coursesList.add(courses);
        }
        return coursesList;
    }

    @RequestMapping(value = "/course/detail", method = RequestMethod.GET)
    public CourseDetailResponse getCourseDetail(@RequestParam(value = "course_id", required = true) String id){
        Course course = courseService.getById(id);
        return new CourseDetailResponse(course.getCourseID().toString(), course.getCourseName(),
                course.getIntroduction(), userService.getById(course.getTeacherID()).getUserName(),
                course.getEndTime().toString(), course.getImgPath(), "false");
    }

    @RequestMapping(value = "/course/add", method = RequestMethod.GET)
    public ResultResponse addCourse(@RequestParam(value = "course_id", required = true) String id, HttpSession session){
        userService.chooseCourse(Integer.parseInt(id), session.getId());
        return new ResultResponse("true", "Add course to your course table successfully!");
    }

    @RequestMapping(value = "/course/create", method = RequestMethod.GET)
    public ResultResponse createCourse(@RequestParam CreateCourseRequest request){
        Course course = new Course();
        course.setTeacherID(request.getTeacher_id());
        course.setCourseName(request.getName());
        course.setIntroduction(request.getDescription());
        course.setStartTime(LocalDateTime.parse(request.getStart_time()));
        course.setEndTime(LocalDateTime.parse(request.getEnd_time()));
        course.setImgPath(request.getImage());
        Boolean flag = courseService.save(course);
        if(flag){
            return new ResultResponse("true", "Create Course successfully!");
        }else{
            return new ResultResponse("false", "Create Course failed!");
        }
    }

    @RequestMapping(value = "/course/delete", method = RequestMethod.GET)
    public ResultResponse deleteCourse(@RequestParam(value = "course_id", required = true) String id){
        Boolean flag = courseService.removeById(id);
        if(flag){
            return new ResultResponse("true", "Delete course successfully!");
        }else{
            return new ResultResponse("false", "Delete course failed!");
        }
    }
}
