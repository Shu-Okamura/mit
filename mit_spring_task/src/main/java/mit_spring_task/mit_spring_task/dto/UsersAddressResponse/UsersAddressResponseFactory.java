package mit_spring_task.mit_spring_task.dto.UsersAddressResponse;

import mit_spring_task.mit_spring_task.dto.UsersResponse.UsersChildResponse;
import mit_spring_task.mit_spring_task.entity.UsersEntity;
import org.springframework.stereotype.Component;


@Component
public class UsersAddressResponseFactory {
    /**
     * DBデータをUserAddressResponse型のインスタンスに入れる
     * @param u DBデータ
     * @return UserAddressResponse型インスタンス
     */
    public UsersAddressResponse toUarp(UsersEntity u){
        UsersChildResponse ucrp = new UsersChildResponse(u.getZipcode(),u.getAddress());
        return new UsersAddressResponse(ucrp);
    }
}
