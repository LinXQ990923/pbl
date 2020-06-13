package fudan.edu.pbl.service.impl;

import fudan.edu.pbl.entity.Task;
import fudan.edu.pbl.mapper.TaskMapper;
import fudan.edu.pbl.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lwy
 * @since 2020-06-13
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

}
