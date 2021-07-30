package mit_spring_task.mit_spring_task.controller;

import mit_spring_task.mit_spring_task.dto.UsersAddressResponse.UsersAddressResponse;
import mit_spring_task.mit_spring_task.dto.UsersRequest.UsersRequestWrapper;
import mit_spring_task.mit_spring_task.dto.UsersResponse.UsersResponseWrapper;
import mit_spring_task.mit_spring_task.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UsersControllerTest {
    @InjectMocks
    private UsersController uc;
    @MockBean
    private UsersService us;
    @BeforeEach
    void beforeEach(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void users_get正常系(){
        String name = "test";
        UsersResponseWrapper expect = new UsersResponseWrapper();
        when(us.findName(name)).thenReturn(expect);
        UsersResponseWrapper actual = uc.users(name);
        verify(us, times(1)).findName(name);
        assertEquals(expect, actual);
    }

    @Test
    public void users_post正常系(){
        UsersRequestWrapper urqw = new UsersRequestWrapper();
        uc.users(urqw);
        verify(us, times(1)).saveAddress(urqw);
        verify(us, times(1)).saveUsers(urqw);
    }

    @Test
    public void address_get正常系(){
        Integer id = 1;
        UsersAddressResponse expect = new UsersAddressResponse();
        when(us.findId(id)).thenReturn(expect);
        UsersAddressResponse actual = us.findId(id);
        verify(us,times(1)).findId(id);
        assertEquals(expect,actual);
    }

    @Test
    public void address_post正常系(){
        uc.address(1,1,"");
        verify(us,times(1)).updateAddress(1,1,"");
    }
}
