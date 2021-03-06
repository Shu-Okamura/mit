package mit_spring_task.mit_spring_task.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class ErrorResource {
    private final Integer code;
    private final List<ErrorResourceDetail> fieldList;
    public static ErrorResource of(Integer code, Set<ConstraintViolation<?>> constraintViolations){
        return new ErrorResource(code,constraintViolations.stream()
                .map(e ->ErrorResourceDetail.of(e.getPropertyPath().toString(), e.getMessage()))
                .collect(Collectors.toList()));
    }
    public static ErrorResource of(Integer code, List<FieldError> fieldErrors){
        return new ErrorResource(code, fieldErrors.stream()
                .map(e -> ErrorResourceDetail.of(e.getField(), e.getDefaultMessage()))
                .collect(Collectors.toList()));
    }
}
