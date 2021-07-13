package mit_spring_task.mit_spring_task.service;

import mit_spring_task.mit_spring_task.dto.UsersResponse;
import mit_spring_task.mit_spring_task.dto.UsersResponseFactory;
import mit_spring_task.mit_spring_task.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UsersMapper um;
    @Autowired
    private UsersResponseFactory urpf;

    // テスト用、全権表示
    public List<UsersResponse> findAll(){
        return urpf.urpfList(um.findAll());
    }

    // nameを受け取って検索
    public  List<UsersResponse> findName(String name){
        return  urpf.urpfList(um.findName(name));
    }
}
