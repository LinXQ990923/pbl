package fudan.edu.pbl.mapper;

import fudan.edu.pbl.entity.Course;
import fudan.edu.pbl.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lwy
 * @since 2020-06-12
 */
@Component
public interface UserMapper extends BaseMapper<User> {
    @Insert("insert into course_user values(#{courseID},#{userID})")
    void chooseCourse(@Param("courseID") int courseID,@Param("userID")String studentID);

    @Insert("insert into program_user values(#{programID},#{userID},#{isLeader},#{grade})")
    void chooseProgram(@Param("programID") int programID,@Param("userID")String studentID,@Param("isLeader") int isLeader,@Param("grade")int grade);

    @Select("select * from course_user cu where cu.courseID = #{courseID} and cu.userID = #{userID} ")
    HashMap<String,Object> ifChooseCourse(@Param("courseID") int courseID, @Param("userID")String studentID);

    @Select("select * from program_user pu where pu.programID = #{programID} and pu.userID = #{userID}")
    HashMap<String,Object> ifChooseProgram(@Param("programID") int programID, @Param("userID")String studentID);

    @Insert("insert into task_user values (#{taskID},#{userID},0)")
    void fetchTask(@Param("taskID") int taskID,@Param("userID") String userID);

    @Update("update task_user tu set isFinish = 1 where tu.taskID=#{taskID} and tu.userID= #{userID} ")
    void finishTask(@Param("taskID") int taskID,@Param("userID")String userID);

    @Update("update program_user pu set grade = #{score} and evaluation = #{evaluation}  where pu.programID and pu.userID = #{userID}")
    void grade(@Param("programID") int programID,@Param("userID") String userID,@Param("score") double score,@Param("evaluation") String evaluation);

    @Select("select * from task_user where userID = #{id}")
    List<HashMap> selectFromTaskUser (String id);

    @Select("select * from program_user where userID = #{id}")
    List<HashMap> selectFromProgramUser (String id);

    User getByIdWithProperties(String id);

    @Select("select * from task_user where taskID = #{id} and isFinish = 1")
    List<HashMap> findFishedTask(int id);

    @Select("select * from task_user where taskID = #{id} and isFinish = 0")
    List<HashMap> findUnfishedTask(int id);


}
