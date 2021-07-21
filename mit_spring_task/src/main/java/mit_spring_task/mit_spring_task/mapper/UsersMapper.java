package mit_spring_task.mit_spring_task.mapper;

import mit_spring_task.mit_spring_task.entity.UsersEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsersMapper {
    List<UsersEntity> findName(String name);
    void saveAddress(UsersEntity u);
    void saveUsers(UsersEntity u);
    UsersEntity findId(Integer id);
    UsersEntity findAddress(Integer id);
    void updateAddress(Integer addressid, Integer zipcode, String address);
}
