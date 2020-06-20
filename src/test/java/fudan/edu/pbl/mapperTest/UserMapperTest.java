package fudan.edu.pbl.mapperTest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import fudan.edu.pbl.entity.*;
import fudan.edu.pbl.mapper.CommentMapper;
import fudan.edu.pbl.mapper.CourseMapper;
import fudan.edu.pbl.mapper.DiscussionMapper;
import fudan.edu.pbl.mapper.UserMapper;
import fudan.edu.pbl.service.*;
import fudan.edu.pbl.service.impl.UserServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;


import java.time.LocalDateTime;
import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;
    @Autowired
    private TaskService taskService;

    @Test
    public void testGeneralSelect() {
        System.out.println(("----- selectAll method test ------"));
        User user = userMapper.selectById("test");
        System.out.println(user.getUserName());
        System.out.println(user.getPhone());
        System.out.println(user.getEmail());
        User user1 = userService.getById("test");
        System.out.println(user1.getUserName() + "hhh");
        assertEquals("test", user.getUserName());
    }


    @Autowired
    CourseMapper courseMapper;
    @Test
    public void testAboutCourse(){
        Course course = new Course();
        course.setCourseName("programming");
        course.setEndTime(LocalDateTime.now());
        course.setStartTime(LocalDateTime.now());
        course.setHasDelete(0);
        course.setImgPath("/test");
        course.setIntroduction("intro");
        course.setTeacherID("test1");
        courseMapper.insert(course);

        User user=userService.getById("test");

        if(userMapper.ifChooseCourse(1,"test")==null)
        userMapper.chooseCourse(1,"test");
        if(userMapper.ifChooseProgram(1,"test")==null)
        userMapper.chooseProgram(1,"test",0);
        Course course1=courseMapper.getCourseByIdWithStudent(1);
        System.out.println(course1.getUserList());
        Course course2 = courseMapper.getCourseByIdWithProgram(1);
        System.out.println(course2.getProgramList());

//        System.out.println(courseMapper.selectById(1).getIntroduction());
    }

    @Test
    public void testAboutTask(){
        Map<String,Object> map = new HashMap();
        map.put("programID",1);
        for(Task task:taskService.listByMap(map)){
            System.out.println(task.getIntroduction());
        }
        userMapper.fetchTask(1,"test");
        userMapper.finishTask(1,"test");


    }

    @Test
    public void testAboutFile(){
        Map<String,Object> map = new HashMap();
        map.put("programID",1);
        for(File file:fileService.listByMap(map)){
            System.out.println(file.getFileName());
        }
    }


    @Test
    public void testUpdate(){
        UpdateWrapper<User> uw = new UpdateWrapper<>();
        uw.eq("userID","test");
        User user = userService.getById("test");
        user.setSchool("fdu");
        userService.update(user,uw);
    }
    @Autowired
    DiscussionMapper discussionMapper;
    @Test
    public void testAboutDiscussion(){
        Discussion discussion=discussionMapper.getDiscussionByIdWithComment(1);
        for(Comment comment:discussion.getCommentList()){
            System.out.println(comment.getContent());
        }
    }

    @Test
    public void testSelect(){
        List<HashMap> map=userMapper.selectFromTaskUser("test");
        //userMapper.fetchTask(1,"test1");
        System.out.println(map);
        List<HashMap> map2=userMapper.selectFromProgramUser("test");
        //userMapper.fetchTask(1,"test1");
        System.out.println(map2);
    }
    @Autowired
    CourseService courseService;
    @Test
    public void test2(){
//        Course course=courseMapper.getCourseByIdWithProgram(1);
//        for (Program program:course.getProgramList()){
//            System.out.println(program.getTaskList());
//        }
        System.out.println(courseService.getCourseByIdWithProgram(1));
        System.out.println(userService.getByIdWithProperties("test"));
        System.out.println(discussionService.getByProgramID(1));
    }
    @Autowired
    DiscussionService discussionService;
    @Test
    public void test3(){
        System.out.println(userService.ifChooseCourse(6,"test"));
        System.out.println(userService.ifChooseCourse(1,"test") );

        System.out.println(userService.ifChooseProgram(1,"test"));
    }

    @Test
    public void test4(){
        System.out.println(userService.findFishedTask(1)==null);
        System.out.println(userService.findFishedTask(1).size());

    }
    @Autowired
    CommentMapper commentMapper;
    @Test
    public void testGrade(){
        //userService.grade(1,"test","test1",2,66.6,"good");
        System.out.println(userMapper.getTeacherGrade(1,"test"));
        System.out.println(userMapper.checkIfGraded(1,"test1","test1"));
        //System.out.println(userMapper.getStudentGrade(1,"test"));
        System.out.println(commentMapper.getCommentsByStudent("test"));
    }


}



