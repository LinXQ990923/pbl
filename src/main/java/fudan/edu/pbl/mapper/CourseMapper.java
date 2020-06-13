package fudan.edu.pbl.mapper;

import fudan.edu.pbl.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lwy
 * @since 2020-06-12
 */
@Component
public interface CourseMapper extends BaseMapper<Course> {
    Course getCourseByIdWithStudent(int id);
    Course getCourseByIdWithProgram(int id);
}
