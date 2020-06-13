package fudan.edu.pbl.mapper;

import fudan.edu.pbl.entity.Program;
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
public interface ProgramMapper extends BaseMapper<Program> {
    Program getProgramByIdWithTask(int id);
}
