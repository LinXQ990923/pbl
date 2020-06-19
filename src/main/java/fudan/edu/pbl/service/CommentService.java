package fudan.edu.pbl.service;

import fudan.edu.pbl.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lwy
 * @since 2020-06-12
 */
public interface CommentService extends IService<Comment> {
    List<Comment> getCommentsByStudent(String id);

}
