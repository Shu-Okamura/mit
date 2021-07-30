package mit_spring_task.mit_spring_task.dto.UsersRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mit_spring_task.mit_spring_task.dto.UsersRequest.UsersRequest;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class UsersRequestWrapper {
    @Valid
    public UsersRequest users;
}
