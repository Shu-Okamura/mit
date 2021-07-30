package mit_spring_task.mit_spring_task.dto.UsersResponse;

import mit_spring_task.mit_spring_task.entity.UsersEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsersResponseFactory {
    /**
     * ユーザー検索結果をリスト化
     * @param uList UsersEntityデータのリスト
     * @return 全ユーザー検索結果のリスト
     */
    public List<UsersResponse> toUrpList(List<UsersEntity> uList){
        return uList.stream().map(this::toUrp).collect(Collectors.toList());
    }

    /**
     * 検索ワードに合致するユーザーデータのインスタンスを生成
     * @param u UsersEntityデータ
     * @return 個々のユーザー検索結果
     */
    public UsersResponse toUrp(UsersEntity u){
        UsersChildResponse ucrp = new UsersChildResponse(u.getZipcode(),u.getAddress());
        return new UsersResponse(u.getName(),u.getAge(),u.getTel(),ucrp);
    }
}
