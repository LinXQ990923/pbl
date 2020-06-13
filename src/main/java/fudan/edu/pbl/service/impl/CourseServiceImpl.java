package fudan.edu.pbl.service.impl;

import fudan.edu.pbl.entity.Course;
import fudan.edu.pbl.mapper.CourseMapper;
import fudan.edu.pbl.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lwy
 * @since 2020-06-12
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    CourseMapper courseMapper;
    public Course getCourseByIdWithStudent(int id){
        return courseMapper.getCourseByIdWithStudent(id);
    }
    public Course getCourseByIdWithProgram(int id){
        return courseMapper.getCourseByIdWithProgram(id);
    }

}
