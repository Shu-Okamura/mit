package mit_spring_task.mit_spring_task.dto.UsersRequest;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class UsersRequest {
    @Valid @NotNull
    private Integer id;
    @Valid @NotBlank
    private String name;
    @Valid @NotNull
    private Integer age;
    @Valid @NotNull
    private Integer tel;
    private UsersChildRequest addressInfo;
}
