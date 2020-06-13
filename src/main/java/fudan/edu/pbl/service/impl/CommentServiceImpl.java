package fudan.edu.pbl.service.impl;

import fudan.edu.pbl.entity.Comment;
import fudan.edu.pbl.mapper.CommentMapper;
import fudan.edu.pbl.service.CommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
