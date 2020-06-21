package fudan.edu.pbl.service;

import fudan.edu.pbl.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lwy
 * @since 2020-06-12
 */
public interface UserService extends IService<User> {

    void chooseCourse( int courseID,String studentID);

    void chooseProgram( int programID,String studentID, int isLeader);

    HashMap ifChooseCourse(int courseID, String studentID);

    HashMap ifChooseProgram( int programID, String studentID);

    void fetchTask( int taskID, String userID);

    void finishTask( int taskID,String userID);

    List<HashMap> selectFromTaskUser (String id);

    List<HashMap> selectFromProgramUser (String id);

    User getByIdWithProperties(String id);

    List<HashMap> findUnfishedTask(int id);

    List<HashMap> findFishedTask(int id);

    void grade(int programID,String userID1,String userID2,int role,double grade,String evaluation);

    Map getTeacherGrade( int programID, String userID1);

    Map checkIfGraded(int programID, String userID1, String userID2);

    Map getStudentGrade(int programID, String userID1);

    Map selectFromGrades(int programID,String userID1);

    List<HashMap> selectFromProgramUserWithProgramID (String id);


}
