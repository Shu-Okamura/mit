package mit_spring_task.mit_spring_task.controller;

import mit_spring_task.mit_spring_task.dto.UsersAddressResponse.UsersAddressResponse;
import mit_spring_task.mit_spring_task.dto.UsersRequest.UsersRequestWrapper;
import mit_spring_task.mit_spring_task.dto.UsersResponse.UsersChildResponse;
import mit_spring_task.mit_spring_task.dto.UsersResponse.UsersResponse;
import mit_spring_task.mit_spring_task.dto.UsersResponse.UsersResponseWrapper;
import mit_spring_task.mit_spring_task.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

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
        UsersResponse urp = new UsersResponse("test",1,1, new UsersChildResponse(1,""));
        UsersResponseWrapper urpw = new UsersResponseWrapper(List.of(urp));
        when(us.findName("test")).thenReturn(urpw);
        UsersResponseWrapper expect = urpw;
        UsersResponseWrapper actual = uc.users("test");
        verify(us, times(1)).findName("test");
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
        UsersAddressResponse uarp = new UsersAddressResponse(new UsersChildResponse(1,""));
        when(us.findId(1)).thenReturn(uarp);
        UsersAddressResponse expect = uarp;
        UsersAddressResponse actual = us.findId(1);
        verify(us,times(1)).findId(1);
        assertEquals(expect,actual);
    }

    @Test
    public void address_post正常系(){
        uc.address(1,1,"");
        verify(us,times(1)).updateAddress(1,1,"");
    }
}
