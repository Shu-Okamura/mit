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
        UsersRequest urq = new UsersRequest(1,"name",2,3,new UsersChildRequest(4,5,"address"));
        UsersRequestWrapper urqw = new UsersRequestWrapper(urq);
        UsersEntity expect = new UsersEntity(1,"name",2,3,4,null,null,5,"address");

        UsersEntity actual = uf.toUser(urqw);

        assertEquals(expect.getId(), actual.getId());
        assertEquals(expect.getName(), actual.getName());
        assertEquals(expect.getAge(), actual.getAge());
        assertEquals(expect.getTel(), actual.getTel());
        assertEquals(expect.getAddressid(), actual.getAddressid());
        assertEquals(expect.getZipcode(), actual.getZipcode());
        assertEquals(expect.getAddress(), actual.getAddress());
    }
}
