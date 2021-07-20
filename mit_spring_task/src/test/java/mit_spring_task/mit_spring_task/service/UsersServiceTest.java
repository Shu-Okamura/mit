package mit_spring_task.mit_spring_task.service;
import mit_spring_task.mit_spring_task.dto.UsersAddressResponse.UsersAddressResponse;
import mit_spring_task.mit_spring_task.dto.UsersAddressResponse.UsersAddressResponseFactory;
import mit_spring_task.mit_spring_task.dto.UsersRequest.UsersRequestWrapper;
import mit_spring_task.mit_spring_task.dto.UsersResponse.*;
import mit_spring_task.mit_spring_task.entity.UsersEntity;
import mit_spring_task.mit_spring_task.entity.UsersEntityFactory;
import mit_spring_task.mit_spring_task.mapper.UsersMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UsersServiceTest {
    @InjectMocks
    private UsersService us;
    @MockBean
    private UsersMapper um;
    @MockBean
    private UsersResponseFactory urpf;
    @MockBean
    private UsersResponseWrapperFactory urpwf;
    @MockBean
    private UsersAddressResponseFactory uarpf;
    @MockBean
    private UsersEntityFactory uf;
    @BeforeEach
    void beforeEach(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findName_正常系(){
        String name = "test_name";
        UsersEntity u = new UsersEntity(1, name,2,3,4,null,null,5,"address");
        List<UsersEntity> uList = List.of(u);
        UsersResponse urp = new UsersResponse(u.getName(),u.getAge(),u.getTel(), new UsersChildResponse(u.getZipcode(),u.getAddress()));
        List<UsersResponse> urpList = List.of(urp);
        UsersResponseWrapper urpw = new UsersResponseWrapper(urpList);

        when(um.findName(name)).thenReturn(uList);
        when(urpf.toUrpList(uList)).thenReturn(urpList);
        when(urpwf.toUrpw(urpList)).thenReturn(urpw);

        UsersResponseWrapper expect = urpw;
        UsersResponseWrapper actual = us.findName(name);

        verify(um, times(1)).findName(name);
        verify(urpf,times(1)).toUrpList(uList);
        verify(urpwf,times(1)).toUrpw(urpList);
        assertEquals(expect, actual);
    }
    @Test
    public void saveAddress_正常系(){
        UsersRequestWrapper urqw = new UsersRequestWrapper();
        UsersEntity u = new UsersEntity();
        us.saveAddress(urqw);
        verify(uf,times(1)).toUser(urqw);
        verify(um,times(1)).saveAddress(u);
    }

    @Test
    public void saveUsers_正常系(){
        UsersRequestWrapper urqw = new UsersRequestWrapper();
        UsersEntity u = new UsersEntity();
        us.saveUsers(urqw);
        verify(uf,times(1)).toUser(urqw);
        verify(um,times(1)).saveUsers(u);
    }
    @Test
    public void findId_正常系(){
        Integer id = 1;
        UsersAddressResponse uarp = new UsersAddressResponse(new UsersChildResponse(5,"address"));
        UsersEntity u = new UsersEntity(id,"name",2,3,4,null,null,5,"address");

        when(um.findId(id)).thenReturn(u);
        when(uarpf.toUarp(u)).thenReturn(uarp);

        UsersAddressResponse expect = uarp;
        UsersAddressResponse actual = us.findId(id);

        verify(uarpf,times(1)).toUarp(u);
        verify(um,times(1)).findId(id);
        assertEquals(expect,actual);
    }
    @Test
    public void updateAddress_正常系(){
        Integer id = 1; Integer zipcode = 2; String address = "address";
        us.updateAddress(id,zipcode,address);
        verify(um,times(1)).updateAddress(id,zipcode,address);
    }
}
