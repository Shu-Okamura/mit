package mit_spring_task.mit_spring_task.entity;
import mit_spring_task.mit_spring_task.dto.UsersRequest.UsersChildRequest;
import mit_spring_task.mit_spring_task.dto.UsersRequest.UsersRequest;
import mit_spring_task.mit_spring_task.dto.UsersRequest.UsersRequestWrapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UsersEntityFactoryTest {
    @InjectMocks
    private UsersEntityFactory uf;
    @BeforeEach
    void beforeEach(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void toUser_正常系(){
        Integer id=1; String name="name"; Integer age=2; Integer tel=3; Integer addressid=4; Integer zipcode=5; String address="address";
        UsersRequestWrapper urqw = new UsersRequestWrapper(new UsersRequest(id,name,age,tel,new UsersChildRequest(addressid,zipcode,address)));
        UsersEntity actual = uf.toUser(urqw);

        assertEquals(id, actual.getId());
        assertEquals(name, actual.getName());
        assertEquals(age, actual.getAge());
        assertEquals(tel, actual.getTel());
        assertEquals(addressid, actual.getAddressid());
        assertEquals(zipcode, actual.getZipcode());
        assertEquals(address, actual.getAddress());
    }
}
