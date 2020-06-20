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
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        if (courseList==null)return null;
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
    public List<CourseResponse> getMyCourses(HttpSession session){
        String user_id=session.getAttribute("id").toString();
        List<Course> courseList = courseService.list();
        if (courseList==null)return null;
        List<CourseResponse> res = new ArrayList<>();
        for (Course course:courseList){
            if (userService.ifChooseCourse(course.getCourseID(),user_id)!=null){
                CourseResponse tmp=new CourseResponse();
                tmp.setId(course.getCourseID().toString());
                tmp.setName(course.getCourseName());
                tmp.setIntroduction(course.getIntroduction());
                tmp.setImage(course.getImgPath());
                res.add(tmp);
            }
        }
        return res;
    }

    @RequestMapping(value = "/teacher/courses/get", method = RequestMethod.GET)
    public List<CourseResponse> getTeacherCourses(HttpSession session){
        String teacher_id=session.getAttribute("id").toString();
        List<Course> courseList = courseService.list();
        if (courseList==null)return null;
        List<CourseResponse> res = new ArrayList<>();
        for (Course course:courseList){
            if (course.getTeacherID().equals(teacher_id)){
                CourseResponse tmp=new CourseResponse();
                tmp.setId(course.getCourseID().toString());
                tmp.setName(course.getCourseName());
                tmp.setIntroduction(course.getIntroduction());
                tmp.setImage(course.getImgPath());
                res.add(tmp);
            }
        }
        return res;
    }


    @RequestMapping(value = "/course/detail", method = RequestMethod.GET)
    public CourseDetailResponse getCourseDetail(@RequestParam(value = "course_id", required = true) String id,HttpSession session){
        Course course = courseService.getById(id);
        session.setAttribute("course_id", id);
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(course != null){
            String isAdd=userService.ifChooseCourse(course.getCourseID(),session.getAttribute("id").toString())==null?"false":"true";
            return new CourseDetailResponse(course.getCourseID().toString(), course.getCourseName(),
                    course.getIntroduction(), userService.getById(course.getTeacherID()).getUserName(),
                    dtf2.format(course.getEndTime()), course.getImgPath(), isAdd);
        }else{
            return null;
        }
    }

    @RequestMapping(value = "/course/add", method = RequestMethod.GET)
    public ResultResponse addCourse(@RequestParam(value = "course_id", required = true) String id, HttpSession session){
        userService.chooseCourse(Integer.parseInt(id), session.getAttribute("id").toString());
        return new ResultResponse("true", "Add course to your course table successfully!");
    }

    @RequestMapping(value = "/course/create", method = RequestMethod.POST)
    public ResultResponse createCourse(@RequestBody CreateCourseRequest request,HttpSession session){
        Course course = new Course();
        course.setTeacherID(session.getAttribute("id").toString());
        course.setCourseName(request.getName());
        course.setIntroduction(request.getDescription());
        LocalDateTime start=LocalDateTime.now();
        int year=Integer.parseInt(request.getEnd_time().split("-")[0]);
        int month=Integer.parseInt(request.getEnd_time().split("-")[1]);
        int day=Integer.parseInt(request.getEnd_time().split("-")[2]);
        course.setStartTime(start);
        course.setEndTime(LocalDateTime.of(year,month,day,start.getHour(),start.getMinute()));
        course.setImgPath(request.getImage());
        boolean flag = courseService.save(course);
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
