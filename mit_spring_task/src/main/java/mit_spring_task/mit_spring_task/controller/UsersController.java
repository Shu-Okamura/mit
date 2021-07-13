package mit_spring_task.mit_spring_task.controller;

import mit_spring_task.mit_spring_task.dto.UsersResponse;
import mit_spring_task.mit_spring_task.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {
    @Autowired
    private UsersService us;

    // テスト：全データ表示
    @GetMapping("/users_all")
    public List<UsersResponse> users_all(){
        return us.findAll();
    }

    // クエリストリング（?name=）を受け取る
    @GetMapping("/users")
    public List<UsersResponse> users(@RequestParam("name") String name){
        return us.findName(name);
    }

    // 関数テスト用
//    @GetMapping("/users_taro")
//    public List<UsersResponse> taro(){
//        return us.findName("太郎");
//    }

}

// 仕様書１の実行結果の表記について、よくわからない箇所があり、以下は無理矢理に表記させたもの。
//String users(@RequestParam("name") String name){
//        return "{ return \"user\": ["+us.findName(name) + "] }";
//    }