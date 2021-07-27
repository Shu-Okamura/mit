package mit_spring_task.mit_spring_task.dto.UsersResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UsersResponseWrapperFactoryTest {
    @InjectMocks
    private UsersResponseWrapperFactory urpwf;
    @BeforeEach
    void beforeEach(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void toUrpw_正常系(){
        String name = "name"; Integer age = 1; Integer tel = 2; Integer zipcode = 3; String address = "address";
        UsersResponseWrapper actual = urpwf.toUrpw(List.of(new UsersResponse(name,age,tel,new UsersChildResponse(zipcode,address))));
        assertEquals(name, actual.getUsers().get(0).getName());
        assertEquals(age, actual.getUsers().get(0).getAge());
        assertEquals(tel, actual.getUsers().get(0).getTel());
        assertEquals(zipcode, actual.getUsers().get(0).getAddressInfo().getZipcode());
        assertEquals(address, actual.getUsers().get(0).getAddressInfo().getAddress());
    }
}
