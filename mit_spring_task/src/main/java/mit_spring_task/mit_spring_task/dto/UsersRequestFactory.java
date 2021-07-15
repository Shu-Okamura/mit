package mit_spring_task.mit_spring_task.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsersRequestFactory {
    /**
     * UserRequestWrapperのデータをUsersRequest型に格納
     * @param urqw UsersRequestWrapperインスタンス
     * @return UserRequestインスタンス
     */
    public UsersRequest toUrq(UsersRequestWrapper urqw){
        return new UsersRequest(
                urqw.users.getId(),
                urqw.users.getName(),
                urqw.users.getAge(),
                urqw.users.getTel(),
                urqw.users.getAddressInfo()
        );
    }
}
