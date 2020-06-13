package fudan.edu.pbl.service.impl;

import fudan.edu.pbl.entity.File;
import fudan.edu.pbl.mapper.FileMapper;
import fudan.edu.pbl.service.FileService;
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
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

}
