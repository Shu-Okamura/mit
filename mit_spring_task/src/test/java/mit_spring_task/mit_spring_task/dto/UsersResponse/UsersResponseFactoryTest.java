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
        UsersResponse urp = new UsersResponse("name", 2 , 3, new UsersChildResponse(5,"address"));
        List<UsersEntity> uList = List.of(new UsersEntity(1,"name",2,3,4,null,null,5,"address"));
        List<UsersResponse> expect = List.of(urp);
        List<UsersResponse> actual = urpf.toUrpList(uList);

        assertEquals(expect.get(0).getName(), actual.get(0).getName());
        assertEquals(expect.get(0).getAge(), actual.get(0).getAge());
        assertEquals(expect.get(0).getTel(), actual.get(0).getTel());
        assertEquals(expect.get(0).getAddressInfo().getZipcode(), actual.get(0).getAddressInfo().getZipcode());
        assertEquals(expect.get(0).getAddressInfo().getAddress(), actual.get(0).getAddressInfo().getAddress());
    }

    @Test
    public void toUrp_正常系(){
        UsersEntity u = new UsersEntity(1,"name",2,3,4,null,null,5,"address");
        UsersResponse expect = new UsersResponse("name",2,3, new UsersChildResponse(5,"address"));
        UsersResponse actual = urpf.toUrp(u);

        assertEquals(expect.getName(), actual.getName());
        assertEquals(expect.getAge(), actual.getAge());
        assertEquals(expect.getTel(), actual.getTel());
        assertEquals(expect.getAddressInfo().getZipcode(), actual.getAddressInfo().getZipcode());
        assertEquals(expect.getAddressInfo().getAddress(), actual.getAddressInfo().getAddress());
    }
}
