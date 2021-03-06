package mit_spring_task.mit_spring_task.functionalTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import lombok.extern.slf4j.Slf4j;
import mit_spring_task.mit_spring_task.util.CsvDataSetLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.File;
import java.net.URL;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@SpringBootTest
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class
})
@Transactional
@EnableWebMvc
public class UsersTest {
    MockMvc mockMvc;
    ClassLoader classLoader;
    @Autowired
    WebApplicationContext webApplicationContext;
    @BeforeEach
    void beforeEach(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        classLoader = this.getClass().getClassLoader();
    }

    @DatabaseSetup("/data/users/init-data/find")
    @Test
    public void users_find_?????????() throws Exception{
        String expected = convertJsonDataToString("expected/users_find.json");
        String data = "?name=??????";
        mockMvc.perform(get("/users" +data))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @DatabaseSetup("/data/users/init-data/find")
    @Test
    public void users_find_?????????_????????????????????????() throws Exception{
        String expected = convertJsonDataToString("expected/users_find_none.json");
        String data = "?name=??????";
        mockMvc.perform(get("/users" +data))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public  void users_find_?????????_??????????????????????????????() throws Exception{
        String data = "?name=";
        String expected = convertJsonDataToString("expected/users_find_failure.json");
        mockMvc.perform(get("/users" +data))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expected));
    }

    @DatabaseSetup("/data/users/init-data/save")
    @ExpectedDatabase(
            value = "/data/users/after-data/save",
            assertionMode = DatabaseAssertionMode.NON_STRICT
    )
    @Test
    public void users_save_?????????() throws Exception{
        String request = convertJsonDataToString("requested/users_save.json");
        mockMvc.perform(post("/users")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void users_save_?????????_????????????????????????() throws Exception{
        mockMvc.perform(post("/users"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void users_save_?????????_??????????????????null() throws Exception{
        String request = convertJsonDataToString("requested/users_save_null.json");
        String expected = convertJsonDataToString("expected/users_save_null.json");
        mockMvc.perform(post("/users").content(request).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expected));
    }

    @DatabaseSetup("/data/users/init-data/find")
    @Test
    public void address_find_?????????() throws Exception{
        String expected = convertJsonDataToString("expected/address_find.json");
        String data = "?id=11";
        mockMvc.perform(get("/address" + data))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @DatabaseSetup("/data/users/init-data/find")
    @Test
    public void address_find_?????????_????????????????????????() throws Exception{
        String expected = convertJsonDataToString("expected/address_find_none.json");
        String data = "?id=99";
        mockMvc.perform(get("/address" + data))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void address_find_?????????_??????????????????????????????() throws Exception{
        String data = "?id=";
        String expected = convertJsonDataToString("expected/address_find_failure.json");
        mockMvc.perform(get("/address" + data))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expected));
    }

    @DatabaseSetup("/data/users/init-data/update")
    @ExpectedDatabase(
            value="/data/users/after-data/update",
            assertionMode = DatabaseAssertionMode.NON_STRICT
    )
    @Test
    public void address_update_?????????() throws Exception{
        String data = "/11?zipcode=11111&address=?????????";
        mockMvc.perform(post("/address" + data))
                .andExpect(status().isOk());
    }


    @Test
    public void address_update_?????????_??????????????????????????????() throws Exception{
        String data = "/1?zipcode=&address=";
        String expected = convertJsonDataToString("expected/address_update_failure.json");
        mockMvc.perform(post("/address" + data))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expected));
    }

    @Test
    public void address_update_?????????_???????????????id??????() throws Exception{
        String data = "/?zipcode=11111&address=?????????";
        mockMvc.perform(post("/address" + data))
                .andExpect(status().isMethodNotAllowed());
    }

    private String convertJsonDataToString(String filePath)throws Exception{
        URL resource = classLoader.getResource(filePath);
        File file = new File(resource.getPath());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(file).toString();
    }
}
