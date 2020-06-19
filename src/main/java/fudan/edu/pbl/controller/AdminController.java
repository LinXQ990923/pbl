package fudan.edu.pbl.controller;


import fudan.edu.pbl.entity.Admin;
import fudan.edu.pbl.entity.Course;
import fudan.edu.pbl.entity.User;
import fudan.edu.pbl.request.CreateCourseRequest;
import fudan.edu.pbl.request.LoginAsAdminRequest;
import fudan.edu.pbl.response.CourseDetailResponse;
import fudan.edu.pbl.response.ResultResponse;
import fudan.edu.pbl.response.StudentResponse;
import fudan.edu.pbl.response.TeacherResponse;
import fudan.edu.pbl.service.AdminService;
import fudan.edu.pbl.service.CourseService;
import fudan.edu.pbl.service.FileService;
import fudan.edu.pbl.service.UserService;
import fudan.edu.pbl.service.impl.AdminServiceImpl;
import fudan.edu.pbl.service.impl.CourseServiceImpl;
import fudan.edu.pbl.service.impl.FileServiceImpl;
import fudan.edu.pbl.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
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
    public ResultResponse adminLogin(@RequestBody LoginAsAdminRequest request, HttpSession session){
        AdminServiceImpl adminService = new AdminServiceImpl();
        Admin admin = adminService.getById(request.getId());
        if(admin != null){
            if(admin.getPassword().equals(request.getPassword())){
                session.setAttribute("id", request.getId());
                session.setAttribute("role", "admin");
                return new ResultResponse("true", "Admin login successfully!");
            }else{
                return new ResultResponse("false", "Password error. Login failed!");
            }
        }else{
            return new ResultResponse("false", "Admin doesn't exist!");
        }
    }

    @RequestMapping(value = "/admin/students", method = RequestMethod.GET)
    public List<StudentResponse> getAllStudent(){
        UserServiceImpl userService = new UserServiceImpl();
        List<StudentResponse> studentList = null;
        List<User> userList = userService.list();
        for(int i = 0; i < userList.size(); i++){
            User user = userList.get(i);
            if(user.getRole() == 0){
                studentList.add(new StudentResponse(
                        user.getUserID(), user.getUserName(), user.getSchool(), user.getDepartment(),
                        user.getEmail(), user.getPhone(), user.getImgPath()));
            }
        }
        return studentList;
    }

    @RequestMapping(value = "/admin/student/delete", method = RequestMethod.GET)
    public ResultResponse deleteStudent(@RequestParam(value = "student_id", required = true) String id){
        UserServiceImpl userService = new UserServiceImpl();
        Boolean flag = userService.removeById(id);
        if(flag){
            return new ResultResponse("true", "Delete student successfully!");
        }else{
            return new ResultResponse("false", "Delete student failed!");
        }
    }

    @RequestMapping(value = "/admin/teachers", method = RequestMethod.GET)
    public List<TeacherResponse> getAllTeacher(){
        UserServiceImpl userService = new UserServiceImpl();
        FileServiceImpl fileService = new FileServiceImpl();
        List<TeacherResponse> teacherList = null;
        List<User> userList = userService.list();
        for(int i = 0; i < userList.size(); i++){
            User user = userList.get(i);
            if(user.getRole() == 1){
                teacherList.add(new TeacherResponse(
                        user.getUserID(), user.getUserName(), user.getSchool(), user.getDepartment(),
                        user.getEmail(), user.getPhone(), user.getImgPath()));
            }
        }
        return teacherList;
    }

    @RequestMapping(value = "/admin/teacher/delete", method = RequestMethod.GET)
    public ResultResponse deleteTeacher(@RequestParam(value = "teacher_id", required = true) String id){
        UserServiceImpl userService = new UserServiceImpl();
        Boolean flag = userService.removeById(id);
        if(flag){
            return new ResultResponse("true", "Delete teacher successfully!");
        }else{
            return new ResultResponse("false", "Delete teacher failed!");
        }
    }

    @RequestMapping(value = "/admin/course/create", method = RequestMethod.POST)
    public ResultResponse createCourse(@RequestBody CreateCourseRequest request){
        CourseServiceImpl courseService = new CourseServiceImpl();
        Course course = new Course();
        course.setTeacherID(request.getTeacher_id());
        course.setCourseName(request.getName());
        course.setIntroduction(request.getDescription());
        course.setStartTime(LocalDateTime.parse(request.getStart_time()));
        course.setEndTime(LocalDateTime.parse(request.getEnd_time()));
        Boolean flag = courseService.save(course);
        if(flag){
            return new ResultResponse("true", "Create Course successfully!");
        }else{
            return new ResultResponse("false", "Create Course failed!");
        }
    }

    @RequestMapping(value = "/admin/courses", method = RequestMethod.GET)
    public List<CourseDetailResponse> getAllCourses(){
        CourseServiceImpl courseService = new CourseServiceImpl();
        UserServiceImpl userService = new UserServiceImpl();
        List<Course> courseList = courseService.list();
        List<CourseDetailResponse> courseDetailList = null;
        CourseDetailResponse courseDetail = null;
        for(int i = 0; i < courseList.size(); i++){
            Course course = courseList.get(i);
            courseDetail.setId(course.getCourseID().toString());
            courseDetail.setName(course.getCourseName());
            courseDetail.setIntroduction(course.getIntroduction());
            courseDetail.setTeacher(userService.getById(course.getTeacherID()).getUserName());
            courseDetail.setEnd_time(course.getEndTime().toString());
            courseDetail.setImage(course.getImgPath());
            courseDetail.setIsAdd("true");
        }
        return courseDetailList;
    }

}
