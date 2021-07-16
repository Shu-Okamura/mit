package mit_spring_task.mit_spring_task.dto.UsersRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mit_spring_task.mit_spring_task.dto.UsersRequest.UsersRequest;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersRequestWrapper {
    public UsersRequest users;
}
