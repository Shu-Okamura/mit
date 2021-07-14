package mit_spring_task.mit_spring_task.controller;

import mit_spring_task.mit_spring_task.dto.UsersRequest;
import mit_spring_task.mit_spring_task.dto.UsersResponse;
import mit_spring_task.mit_spring_task.dto.UsersResponseWrapper;
import mit_spring_task.mit_spring_task.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@Validated
public class UsersController {
    @Autowired
    private UsersService us;

    /**
     * ユーザー情報を検索
     * @param name ユーザー名
     * @return ユーザー情報検索結果
     */
    @GetMapping("/users")
    public UsersResponseWrapper users(@Valid @NotBlank @RequestParam("name") String name){
        return us.findName(name);
    }

    @PostMapping("/users")
    public void users(@RequestBody UsersRequest user){
        us.save(user);
    }

}