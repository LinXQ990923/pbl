package fudan.edu.pbl.service.impl;

import fudan.edu.pbl.entity.Admin;
import fudan.edu.pbl.mapper.AdminMapper;
import fudan.edu.pbl.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
