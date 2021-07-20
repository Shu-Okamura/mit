package mit_spring_task.mit_spring_task.entity;

import mit_spring_task.mit_spring_task.dto.UsersRequest.UsersRequestWrapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class UsersEntityFactory {
    /**
     * 受け取ったUserRequestWrapper型のデータをUserEntityに入れる
     * @param urqw UserRequestWrapper型のインスタンス
     * @return UserEntity型のインスタンス
     */
    public UsersEntity toUser(UsersRequestWrapper urqw){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        return new UsersEntity(
                urqw.getUsers().getId(),
                urqw.getUsers().getName(),
                urqw.getUsers().getAge(),
                urqw.getUsers().getTel(),
                urqw.getUsers().getAddressInfo().getAddressid(),
                ts,
                ts,
                urqw.getUsers().getAddressInfo().getZipcode(),
                urqw.getUsers().getAddressInfo().getAddress()
        );
    }
}
