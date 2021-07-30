package mit_spring_task.mit_spring_task.service;

import mit_spring_task.mit_spring_task.dto.UsersAddressResponse.UsersAddressResponse;
import mit_spring_task.mit_spring_task.dto.UsersAddressResponse.UsersAddressResponseFactory;
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
    private UsersEntityFactory uf;
    @Autowired
    private UsersAddressResponseFactory uarpf;

    /**
     * SQLのfindNameを呼び出し、UserResponseFactoryのリスト化処理を呼び出す
     * @param name Controllerから受け取ったユーザー名
     * @return ユーザー検索結果のリスト
     */
    public UsersResponseWrapper findName(String name){
        List<UsersEntity> uList = um.findName(name);
        List<UsersResponse> urpList = urpf.toUrpList(uList);
        return  urpwf.toUrpw(urpList);
    }

    /**
     * 受け取ったデータをUserEntity型に変換し、それぞれSQLメソッドで登録処理をする
     * @param urqw　Postされたデータ
     */
    @Transactional
    public void saveAddress(UsersRequestWrapper urqw){
        um.saveAddress(uf.toUser(urqw));
    }

    /**
     * 受け取ったデータをUserEntity型に変換し、それぞれSQLメソッドで登録処理をする
     * @param urqw Postされたデータ
     */
    @Transactional
    public void saveUsers(UsersRequestWrapper urqw){
        um.saveUsers(uf.toUser(urqw));
    }

    /**
     * PostされたIDをもとに、ユーザーの住所情報を返す
     * @param id ユーザーID
     * @return UserEntityインスタンス
     */
    public UsersAddressResponse findId(Integer id){
        UsersEntity u = um.findId(id);
        if(u == null){
            u = new UsersEntity();
        }
        return uarpf.toUarp(u);
    }

    /**
     * ユーザーIDを元に検索し、、そのユーザーの郵便番号と住所を更新する
     * @param id ユーザーID
     * @param zipcode 郵便番号
     * @param address 住所
     */
    @Transactional
    public void updateAddress(Integer id, Integer zipcode, String address){
        UsersEntity u = um.findAddress(id);
        if(u != null){
            um.updateAddress(u.getAddressid(), zipcode, address);
        }
    }
}
