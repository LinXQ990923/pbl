package fudan.edu.pbl.service.impl;

import fudan.edu.pbl.entity.Discussion;
import fudan.edu.pbl.mapper.DiscussionMapper;
import fudan.edu.pbl.service.DiscussionService;
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
public class DiscussionServiceImpl extends ServiceImpl<DiscussionMapper, Discussion> implements DiscussionService {
    @Autowired
    DiscussionMapper discussionMapper;
    public Discussion getDiscussionByIdWithComment(int id){
        return discussionMapper.getDiscussionByIdWithComment(id);
    }

    public Discussion getByProgramID(int id){
        return discussionMapper.getByProgramID(id);
    }
}
