package mit_spring_task.mit_spring_task.dto;

import mit_spring_task.mit_spring_task.entity.UsersEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsersResponseFactory {
    // urpインスタンスをリストとして入れる
    public List<UsersResponse> urpfList(List<UsersEntity> u){
        return u.stream().map(this::urp).collect(Collectors.toList());
    }

    // UserResponseインスタンス作成
    public UsersResponse urp(UsersEntity u){
        UsersChildResponse ucrp = new UsersChildResponse(u.getZipcode(),u.getAddress());
        return new UsersResponse(u.getName(),u.getAge(),u.getTel(),ucrp);
    }
}
