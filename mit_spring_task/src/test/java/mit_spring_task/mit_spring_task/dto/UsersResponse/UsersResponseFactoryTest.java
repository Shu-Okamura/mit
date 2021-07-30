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
        List<UsersEntity> uList = List.of(new UsersEntity(1,"name",2,3,4,null,null,5,"address"));
        List<UsersResponse> actual = urpf.toUrpList(uList);

        assertEquals("name", actual.get(0).getName());
        assertEquals(2, actual.get(0).getAge());
        assertEquals(3, actual.get(0).getTel());
        assertEquals(5, actual.get(0).getAddressInfo().getZipcode());
        assertEquals("address", actual.get(0).getAddressInfo().getAddress());
    }
}
