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
        UsersResponseWrapper actual = urpwf.toUrpw(List.of(new UsersResponse("name",1,2,new UsersChildResponse(3,"address"))));
        assertEquals("name", actual.getUsers().get(0).getName());
        assertEquals(1, actual.getUsers().get(0).getAge());
        assertEquals(2, actual.getUsers().get(0).getTel());
        assertEquals(3, actual.getUsers().get(0).getAddressInfo().getZipcode());
        assertEquals("address", actual.getUsers().get(0).getAddressInfo().getAddress());
    }
}
