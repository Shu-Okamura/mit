package mit_spring_task.mit_spring_task.dto.UsersResponse;
import mit_spring_task.mit_spring_task.entity.UsersEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UsersResponseFactoryTest {
    @InjectMocks
    private UsersResponseFactory urpf;
    @BeforeEach
    void beforeEach(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void toUrpList_正常系(){
        String name = "name"; Integer age = 1; Integer tel = 2; Integer zipcode = 3; String address = "address";
        List<UsersEntity> uList = List.of(new UsersEntity(1,name,age,tel,11,null,null,zipcode,address));
        List<UsersResponse> actual = urpf.toUrpList(uList);

        assertEquals(name, actual.get(0).getName());
        assertEquals(age, actual.get(0).getAge());
        assertEquals(tel, actual.get(0).getTel());
        assertEquals(zipcode, actual.get(0).getAddressInfo().getZipcode());
        assertEquals(address, actual.get(0).getAddressInfo().getAddress());
    }
}
