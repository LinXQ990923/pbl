package fudan.edu.pbl.service;

import fudan.edu.pbl.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lwy
 * @since 2020-06-12
 */
public interface UserService extends IService<User> {
    void grade( int programID, String userID,double score);

    void chooseCourse( int courseID,String studentID);

    void chooseProgram( int programID,String studentID, int isLeader,int grade);


    int ifChooseCourse(int courseID, String studentID);


    int ifChooseProgram( int programID, String studentID);

    void fetchTask( int taskID, String userID);


    void finishTask( int taskID,String userID);

    List<HashMap> selectFromTaskUser (String id);

    List<HashMap> selectFromProgramUser (String id);

    User getByIdWithProperties(String id);

}
