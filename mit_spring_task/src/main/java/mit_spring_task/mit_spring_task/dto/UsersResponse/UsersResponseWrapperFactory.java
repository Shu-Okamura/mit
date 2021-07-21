package mit_spring_task.mit_spring_task.dto.UsersResponse;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsersResponseWrapperFactory {
    public UsersResponseWrapper toUrpw(List<UsersResponse> urp){
        return new UsersResponseWrapper(urp);
    }
}
