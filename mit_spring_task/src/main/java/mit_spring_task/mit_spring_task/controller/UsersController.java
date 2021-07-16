package mit_spring_task.mit_spring_task.controller;

import mit_spring_task.mit_spring_task.dto.UsersRequestWrapper;
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
    UsersResponseWrapper users(@Valid @NotBlank @RequestParam("name") String name){
        return us.findName(name);
    }

    /**
     * ユーザー情報を登録
     * @param urqw ユーザー情報
     */
    @PostMapping("/users")
    void users(@RequestBody UsersRequestWrapper urqw){
        us.save(urqw);
    }

}