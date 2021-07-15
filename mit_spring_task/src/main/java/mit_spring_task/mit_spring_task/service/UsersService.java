package mit_spring_task.mit_spring_task.service;

import mit_spring_task.mit_spring_task.dto.*;
import mit_spring_task.mit_spring_task.entity.UsersEntity;
import mit_spring_task.mit_spring_task.entity.UsersEntityFactory;
import mit_spring_task.mit_spring_task.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private UsersRequestFactory urqf;
    @Autowired
    private UsersEntityFactory uf;

    /**
     * SQLのfindNameを呼び出し、UserResponseFactoryのリスト化処理を呼び出す
     * @param name Controllerから受け取ったユーザー名
     * @return ユーザー検索結果のリスト
     */
    public UsersResponseWrapper findName(String name){
        List<UsersResponse> urpList = urpf.urpfList(um.findName(name));
        return  urpwf.toUrpw(urpList);
    }

    /**
     * 受け取ったデータをUserEntity型に変換し、それぞれSQLメソッドで登録処理をする
     * @param urqw　Postされたデータ
     */
    @Transactional
    public void save(UsersRequestWrapper urqw){
        UsersEntity u = uf.toUser(urqf.toUrq(urqw));
        um.saveAddress(u);
        um.saveUsers(u);
    }
}
