package mit_spring_task.mit_spring_task.entity;

import mit_spring_task.mit_spring_task.dto.UsersRequest;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class UsersEntityFactory {
    /**
     * 受け取ったUserRequest型のデータをUserEntityに入れる
     * @param urq UserRequest型のインスタンス
     * @return UserEntity型のインスタンス
     */
    public UsersEntity toUser(UsersRequest urq){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        return new UsersEntity(
                urq.getId(),
                urq.getName(),
                urq.getAge(),
                urq.getTel(),
                urq.getAddressInfo().getAddressid(),
                ts,
                ts,
                urq.getAddressInfo().getZipcode(),
                urq.getAddressInfo().getAddress()
        );
    }
}
