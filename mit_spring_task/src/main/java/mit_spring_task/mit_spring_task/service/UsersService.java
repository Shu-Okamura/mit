package mit_spring_task.mit_spring_task.service;

import mit_spring_task.mit_spring_task.dto.UsersAddressResponse.UsersAddressResponse;
import mit_spring_task.mit_spring_task.dto.UsersAddressResponse.UsersAddressResponseFactory;
import mit_spring_task.mit_spring_task.dto.UsersRequest.UsersRequestFactory;
import mit_spring_task.mit_spring_task.dto.UsersRequest.UsersRequestWrapper;
import mit_spring_task.mit_spring_task.dto.UsersResponse.UsersResponse;
import mit_spring_task.mit_spring_task.dto.UsersResponse.UsersResponseFactory;
import mit_spring_task.mit_spring_task.dto.UsersResponse.UsersResponseWrapper;
import mit_spring_task.mit_spring_task.dto.UsersResponse.UsersResponseWrapperFactory;
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
    @Autowired
    private UsersAddressResponseFactory uarpf;

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
    public void saveAddress(UsersRequestWrapper urqw){
        UsersEntity u = uf.toUser(urqf.toUrq(urqw));
        um.saveAddress(u);
    }
    @Transactional
    public void saveUsers(UsersRequestWrapper urqw){
        UsersEntity u = uf.toUser(urqf.toUrq(urqw));
        um.saveUsers(u);
    }
    public UsersAddressResponse findId(Integer id){
        UsersEntity u = um.findId(id);
        return uarpf.toUarp(u);
    }
    @Transactional
    public void updateAddress(Integer id, Integer zipcode, String address){
        UsersEntity u = um.findAddress(id);
        Integer addressid = u.getAddressid();
        um.updateAddress(addressid, zipcode, address);
    }
}
