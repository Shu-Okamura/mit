package mit_spring_task.mit_spring_task.service;

import mit_spring_task.mit_spring_task.dto.UsersResponse;
import mit_spring_task.mit_spring_task.dto.UsersResponseFactory;
import mit_spring_task.mit_spring_task.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UsersMapper um;
    @Autowired
    private UsersResponseFactory urpf;

    /**
     * SQLのfindNameを呼び出し、UserResponseFactoryのリスト化処理を呼び出す
     * @param name Controllerから受け取ったユーザー名
     * @return ユーザー検索結果のリスト
     */
    public  List<UsersResponse> findName(String name){
        return  urpf.urpfList(um.findName(name));
    }
}
