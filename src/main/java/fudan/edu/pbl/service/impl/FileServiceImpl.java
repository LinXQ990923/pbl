package fudan.edu.pbl.service.impl;

import fudan.edu.pbl.controller.FileNameUtil;
import fudan.edu.pbl.entity.File;
import fudan.edu.pbl.mapper.FileMapper;
import fudan.edu.pbl.service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
    //支持的图片类型
    private static final List<String> CONTENT_TYPE = Arrays.asList("image/gif", "image/jpeg","image/png");
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public String uploadImage(MultipartFile file, String imageName) {

        String newFileName = FileNameUtil.getFileName(file.getOriginalFilename());
        System.out.println(file.getOriginalFilename());
        System.out.println(newFileName);
        String contentType = file.getContentType();
        if (!CONTENT_TYPE.contains(contentType)) {
            logger.info("文件类型不合法:{}", newFileName);
            return null;
        }
        String path = imageName+newFileName;
        try {
            BufferedImage read = ImageIO.read(file.getInputStream());
            if (read == null) {
                logger.info("文件内容不合法:{}", newFileName);
                return null;
            }

            java.io.File dest = new java.io.File(path);
            System.out.println(dest.getParentFile());
            //判断文件父目录是否存在
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();
            }

            file.transferTo(dest);
        } catch (IOException e) {
            logger.error("服务器内部错误->:{}", newFileName);
            e.printStackTrace();
        }//返回图片路径

        return newFileName;
    }
}
