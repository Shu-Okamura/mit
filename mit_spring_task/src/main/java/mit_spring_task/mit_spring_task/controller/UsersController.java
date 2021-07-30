package mit_spring_task.mit_spring_task.controller;

import mit_spring_task.mit_spring_task.dto.UsersAddressResponse.UsersAddressResponse;
import mit_spring_task.mit_spring_task.dto.UsersRequest.UsersRequestWrapper;
import mit_spring_task.mit_spring_task.dto.UsersResponse.UsersResponseWrapper;
import mit_spring_task.mit_spring_task.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    void users(@Valid @RequestBody UsersRequestWrapper urqw){
        us.saveAddress(urqw);
        us.saveUsers(urqw);
    }

    @GetMapping("/address")
    UsersAddressResponse address(@Valid @NotNull @RequestParam(name="id",required = false) Integer id){
        return us.findId(id);
    }

    @PostMapping("/address/{id}")
    void address(@Valid @NotNull @PathVariable("id") Integer id,
                 @Valid @NotNull @RequestParam(name="zipcode", required = false)Integer zipcode,
                 @Valid @NotBlank @RequestParam(name="address", required = false)String address){
        us.updateAddress(id,zipcode,address);
    }

}