package mit_spring_task.mit_spring_task.dto.UsersAddressResponse;
import mit_spring_task.mit_spring_task.dto.UsersResponse.UsersChildResponse;
import mit_spring_task.mit_spring_task.entity.UsersEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UsersAddressResponseFactoryTest {
    @InjectMocks
    private UsersAddressResponseFactory uarpf;
    @BeforeEach
    void beforeEach(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void toUarpw_正常系(){
        UsersEntity u = new UsersEntity(1,"name",2,3,4,null,null,5,"address");
        UsersAddressResponse expect = new UsersAddressResponse(new UsersChildResponse(5,"address"));
        UsersAddressResponse actual = uarpf.toUarp(u);
        assertEquals(expect.getAddressInfo().getZipcode(),actual.getAddressInfo().getZipcode());
        assertEquals(expect.getAddressInfo().getAddress(),actual.getAddressInfo().getAddress());
    }
}
