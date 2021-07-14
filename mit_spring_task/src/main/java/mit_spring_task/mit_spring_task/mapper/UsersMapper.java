package mit_spring_task.mit_spring_task.mapper;

import mit_spring_task.mit_spring_task.entity.UsersEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsersMapper {
    List<UsersEntity> findName(String name);
}
