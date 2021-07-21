package mit_spring_task.mit_spring_task.dto.UsersAddressResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mit_spring_task.mit_spring_task.dto.UsersResponse.UsersChildResponse;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersAddressResponse {
    private UsersChildResponse addressInfo;
}
