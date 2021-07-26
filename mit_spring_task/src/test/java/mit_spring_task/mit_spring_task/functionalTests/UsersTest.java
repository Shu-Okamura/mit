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

    @DatabaseSetup("/data/find")
    @Test
    public void users_find_正常系() throws Exception{
        String expected = convertJsonDataToString("expected/users_find.json");
        mockMvc.perform(get("/users?name=太郎"))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    // dataseterror
    @DatabaseSetup("/data/users/save")
    @ExpectedDatabase(
            value = "",
            assertionMode = DatabaseAssertionMode.DEFAULT
    )
    @Test
    public void users_save_正常系() throws Exception{
        String request = convertJsonDataToString("requested/users_save.json");
        mockMvc.perform(post("/users"))
                .andExpect(status().isOk());
    }

    // 現状：AssertionError actualが400で帰ってくる、おそらくURLパラムを渡せていないから
    @DatabaseSetup("/data/find")
    @Test
    public void address_find_正常系() throws Exception{
        String expected = convertJsonDataToString("expected/users_find.json");
        mockMvc.perform(post("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @DatabaseSetup("/data/users/update")
    @ExpectedDatabase("")
    @Test
    public void address_update_正常系() throws Exception{
        String request = convertJsonDataToString("expected/users_find.json");
        mockMvc.perform(post("/users"))
                .andExpect(status().isOk());
    }

    private String convertJsonDataToString(String filePath)throws Exception{
        URL resource = classLoader.getResource(filePath);
        File file = new File(resource.getPath());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(file).toString();
    }
}
