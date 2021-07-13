package mit_spring_task.mit_spring_task.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersEntity {
    private Integer id;
    private String name;
    private Integer age;
    private Integer tel;
    private Integer addressid;
    private Timestamp createdate;
    private Timestamp updatedate;
    private Integer zipcode;
    private String address;
}
