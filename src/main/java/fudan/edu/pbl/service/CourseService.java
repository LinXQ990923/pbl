package fudan.edu.pbl.service;

import fudan.edu.pbl.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lwy
 * @since 2020-06-12
 */
public interface CourseService extends IService<Course> {
    Course getCourseByIdWithStudent(int id);
    Course getCourseByIdWithProgram(int id);
}
