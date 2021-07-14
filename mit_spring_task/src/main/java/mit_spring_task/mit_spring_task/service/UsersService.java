package mit_spring_task.mit_spring_task.service;

import mit_spring_task.mit_spring_task.dto.*;
import mit_spring_task.mit_spring_task.entity.UsersEntityFactory;
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
    @Autowired
    private UsersResponseWrapperFactory urpwf;
    @Autowired
    private UsersEntityFactory uef;

    /**
     * SQLのfindNameを呼び出し、UserResponseFactoryのリスト化処理を呼び出す
     * @param name Controllerから受け取ったユーザー名
     * @return ユーザー検索結果のリスト
     */
    public UsersResponseWrapper findName(String name){
        List<UsersResponse> urpList = urpf.urpfList(um.findName(name));
        return  urpwf.toUrpw(urpList);
    }

    public void save(UsersRequest urq){
        um.save(uef.toUser(urq));
    }
}
