package mit_spring_task.mit_spring_task.entity;

import mit_spring_task.mit_spring_task.dto.UsersRequest;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class UsersEntityFactory {
    public UsersEntity toUser(UsersRequest urq){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        return new UsersEntity(urq.getId(),urq.getName(),urq.getAge(),urq.getTel(),urq.getAddressinfo().getAddressid(),ts,ts,urq.getAddressinfo().getZipcode(),urq.getAddressinfo().getAddress());
    }
}
