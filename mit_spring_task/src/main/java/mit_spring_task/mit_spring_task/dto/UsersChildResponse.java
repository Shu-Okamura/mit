package mit_spring_task.mit_spring_task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersChildResponse {
    private Integer zipcode;
    private String address;
}
