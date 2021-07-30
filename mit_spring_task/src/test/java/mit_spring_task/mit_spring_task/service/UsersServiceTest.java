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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UsersServiceTest {
    @InjectMocks
    private UsersService us;
    @MockBean(name = "um")
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
        String name = "name";
        List<UsersEntity> uList = new ArrayList<>();
        List<UsersResponse> urpList = new ArrayList<>();
        UsersResponseWrapper expect = new UsersResponseWrapper();
        when(um.findName(name)).thenReturn(uList);
        when(urpf.toUrpList(uList)).thenReturn(urpList);
        when(urpwf.toUrpw(urpList)).thenReturn(expect);
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
        when(uf.toUser(urqw)).thenReturn(u);
        us.saveAddress(urqw);
        verify(uf,times(1)).toUser(urqw);
        verify(um,times(1)).saveAddress(u);
    }

    @Test
    public void saveUsers_正常系(){
        UsersRequestWrapper urqw = new UsersRequestWrapper();
        UsersEntity u = new UsersEntity();
        when(uf.toUser(urqw)).thenReturn(u);
        us.saveUsers(urqw);
        verify(uf,times(1)).toUser(urqw);
        verify(um,times(1)).saveUsers(u);
    }

    @Test
    public void findId_正常系(){
        Integer id = 1;
        UsersAddressResponse expect = new UsersAddressResponse();
        UsersEntity u = new UsersEntity();
        when(um.findId(id)).thenReturn(u);
        when(uarpf.toUarp(u)).thenReturn(expect);
        UsersAddressResponse actual = us.findId(id);
        verify(uarpf,times(1)).toUarp(u);
        verify(um,times(1)).findId(id);
        assertEquals(expect,actual);
    }

    @Test
    public void findId_正常系_マッチデータ無し(){
        Integer id = 10;
        UsersAddressResponse expect = new UsersAddressResponse();
        UsersEntity u = new UsersEntity();
        when(um.findId(id)).thenReturn(null);
        when(uarpf.toUarp(any(UsersEntity.class))).thenReturn(expect);
        UsersAddressResponse actual = us.findId(id);
        verify(um,times(1)).findId(id);
        verify(uarpf,times(1)).toUarp(any(UsersEntity.class));
        assertEquals(expect,actual);
    }

    @Test
    public void updateAddress_正常系(){
        Integer id = 1;
        Integer zipcode = 5;
        String address = "new address";
        UsersEntity u = new UsersEntity();
        when(um.findAddress(id)).thenReturn(u);
        doNothing().when(um).updateAddress(u.getAddressid(),zipcode,address);
        us.updateAddress(id, zipcode, address);
        verify(um,times(1)).findAddress(id);
        verify(um,times(1)).updateAddress(u.getAddressid(), zipcode, address);
    }

    @Test
    public void updateAddress_正常系_マッチデータ無し(){
        Integer id = 10;
        Integer zipcode = 5;
        String address = "address";
        UsersEntity u = new UsersEntity();
        when(um.findAddress(id)).thenReturn(null);
        us.updateAddress(id, zipcode, address);
        verify(um,times(1)).findAddress(id);
        verify(um,times(0)).updateAddress(anyInt(), anyInt(),anyString());
    }
}
