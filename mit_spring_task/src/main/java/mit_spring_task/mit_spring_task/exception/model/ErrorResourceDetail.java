package mit_spring_task.mit_spring_task.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResourceDetail {
    private final String name;
    private final String message;
    public static ErrorResourceDetail of(String name, String message){
        return new ErrorResourceDetail(name,message);
    }

}
