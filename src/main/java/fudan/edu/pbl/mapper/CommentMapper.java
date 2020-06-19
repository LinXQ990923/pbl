package fudan.edu.pbl.mapper;

import fudan.edu.pbl.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lwy
 * @since 2020-06-12
 */
@Component
public interface CommentMapper extends BaseMapper<Comment> {
    @Select("select * from comment where userID = #{id}")
    List<Comment> getCommentsByStudent(String id);

}
