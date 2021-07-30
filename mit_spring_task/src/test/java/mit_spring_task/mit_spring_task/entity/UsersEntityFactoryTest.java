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
        UsersRequestWrapper urqw = new UsersRequestWrapper(new UsersRequest(1,"name",2,3,new UsersChildRequest(4,5,"address")));
        UsersEntity actual = uf.toUser(urqw);

        assertEquals(1, actual.getId());
        assertEquals("name", actual.getName());
        assertEquals(2, actual.getAge());
        assertEquals(3, actual.getTel());
        assertEquals(4, actual.getAddressid());
        assertEquals(5, actual.getZipcode());
        assertEquals("address", actual.getAddress());
    }
}
