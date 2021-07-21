package mit_spring_task.mit_spring_task.dto.UsersResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponse {
    private String name;
    private Integer age;
    private Integer tel;
    private UsersChildResponse addressInfo;
}
