package fudan.edu.pbl.service;

import fudan.edu.pbl.entity.Discussion;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lwy
 * @since 2020-06-12
 */
public interface DiscussionService extends IService<Discussion> {
    Discussion getDiscussionByIdWithComment(int id);
    Discussion getByProgramID(int id);
}
