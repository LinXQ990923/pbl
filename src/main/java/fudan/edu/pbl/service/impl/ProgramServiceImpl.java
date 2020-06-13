package fudan.edu.pbl.service.impl;

import fudan.edu.pbl.entity.Program;
import fudan.edu.pbl.mapper.ProgramMapper;
import fudan.edu.pbl.service.ProgramService;
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
public class ProgramServiceImpl extends ServiceImpl<ProgramMapper, Program> implements ProgramService {
    @Autowired
    ProgramMapper programMapper;
    public Program getProgramByIdWithTask(int id){
        return programMapper.getProgramByIdWithTask(id);
    }
}
