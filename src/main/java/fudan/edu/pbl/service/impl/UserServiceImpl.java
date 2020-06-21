package fudan.edu.pbl.service.impl;

import fudan.edu.pbl.entity.User;
import fudan.edu.pbl.mapper.UserMapper;
import fudan.edu.pbl.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lwy
 * @since 2020-06-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;


    public void chooseCourse( int courseID,String studentID){
        userMapper.chooseCourse(courseID,studentID);
    }

    public void chooseProgram( int programID,String studentID, int isLeader){
        userMapper.chooseProgram(programID, studentID, isLeader);
    }


    public HashMap<String, Object> ifChooseCourse(int courseID, String studentID){
        return userMapper.ifChooseCourse(courseID, studentID);
    }


    public HashMap<String, Object> ifChooseProgram(int programID, String studentID){
        return userMapper.ifChooseProgram(programID, studentID);
    }

    public void fetchTask( int taskID, String userID){
         userMapper.finishTask(taskID, userID);
    }


    public void finishTask( int taskID,String userID){
        userMapper.finishTask(taskID, userID);
    }

    public List<HashMap> selectFromTaskUser (String id){
        return userMapper.selectFromTaskUser(id);
    }

    public List<HashMap> selectFromProgramUser (String id){
        return userMapper.selectFromProgramUser(id);
    }

    public User getByIdWithProperties(String id){
        return userMapper.getByIdWithProperties(id);
    }

    public List<HashMap> findUnfishedTask(int id){
        return userMapper.findUnfishedTask(id);
    }

    public List<HashMap> findFishedTask(int id){
        return userMapper.findFishedTask(id);
    }

    public void grade(int programID,String userID1,String userID2,int role,double grade,String evaluation){
        userMapper.grade(programID,userID1,userID2,role,grade,evaluation);
    }

    public Map getTeacherGrade(int programID, String userID1){
        return userMapper.getTeacherGrade(programID,userID1);
    }

    public Map checkIfGraded(int programID, String userID1, String userID2){
        return userMapper.checkIfGraded(programID,userID1,userID2);
    }

    public Map getStudentGrade(int programID, String userID1){
        return userMapper.getStudentGrade(programID,userID1);
    }

    public Map selectFromGrades(int programID,String userID1){
        return userMapper.selectFromGrades(programID,userID1);
    }

    public List<HashMap> selectFromProgramUserWithProgramID (String id){
        return userMapper.selectFromProgramUserWithProgramID(id);
    }

}
