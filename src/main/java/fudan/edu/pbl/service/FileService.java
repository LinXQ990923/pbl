package fudan.edu.pbl.service;

import fudan.edu.pbl.entity.File;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lwy
 * @since 2020-06-12
 */
public interface FileService extends IService<File> {
    public String uploadImage(MultipartFile file, String imageName) ;

}
