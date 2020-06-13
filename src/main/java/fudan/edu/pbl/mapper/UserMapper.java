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

    @Select("select count(*) from course_user cu where cu.courseID = #{courseID} and cu.userID = #{userID} ")
    int ifChooseCourse(@Param("courseID") int courseID, @Param("userID")String studentID);

    @Select("select count(*) from program_user pu where pu.programID = #{programID} and pu.userID = #{userID}")
    int ifChooseProgram(@Param("programID") int programID, @Param("userID")String studentID);

    @Insert("insert into task_user values (#{taskID},#{userID},0)")
    void fetchTask(@Param("taskID") int taskID,@Param("userID") String userID);

    @Update("update task_user tu set isFinish = 1 where tu.taskID=#{taskID} and tu.userID= #{userID} ")
    void finishTask(@Param("taskID") int taskID,@Param("userID")String userID);

    @Update("update program_user pu set grade = #{score} where pu.programID and pu.userID = #{userID}")
    void grade(@Param("programID") int programID,@Param("userID") String userID,@Param("score") double score);

    @Select("select * from task_user where userID = #{id}")
    List<HashMap> selectFromTaskUser (String id);

    @Select("select * from program_user where userID = #{id}")
    List<HashMap> selectFromProgramUser (String id);
}
