package fudan.edu.pbl.service;

import fudan.edu.pbl.entity.Program;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lwy
 * @since 2020-06-12
 */
public interface ProgramService extends IService<Program> {
    Program getProgramByIdWithTask(int id);
}
