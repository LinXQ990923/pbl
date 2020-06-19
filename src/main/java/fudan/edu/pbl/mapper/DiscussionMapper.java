package fudan.edu.pbl.mapper;

import fudan.edu.pbl.entity.Discussion;
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
public interface DiscussionMapper extends BaseMapper<Discussion> {
    Discussion getDiscussionByIdWithComment(int id);
    @Select("select * from discussion where programID = #{id}")
    Discussion getByProgramID(int id);
}
