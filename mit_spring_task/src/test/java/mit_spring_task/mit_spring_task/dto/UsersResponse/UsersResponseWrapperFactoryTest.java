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
        UsersResponseWrapper expect = new UsersResponseWrapper(List.of(new UsersResponse("name",1,2,new UsersChildResponse(1,"address"))));
        UsersResponseWrapper actual = urpwf.toUrpw(List.of(new UsersResponse("name",1,2,new UsersChildResponse(1,"address"))));
        assertEquals(expect.getUsers().get(0).getName(), actual.getUsers().get(0).getName());
        assertEquals(expect.getUsers().get(0).getAge(), actual.getUsers().get(0).getAge());
        assertEquals(expect.getUsers().get(0).getTel(), actual.getUsers().get(0).getTel());
        assertEquals(expect.getUsers().get(0).getAddressInfo().getZipcode(), actual.getUsers().get(0).getAddressInfo().getZipcode());
        assertEquals(expect.getUsers().get(0).getAddressInfo().getAddress(), actual.getUsers().get(0).getAddressInfo().getAddress());

    }
}
