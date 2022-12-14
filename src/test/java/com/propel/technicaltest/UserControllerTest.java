package com.propel.technicaltest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.io.IOException;
import java.util.List;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private MockMvc mockMvc;

    private final List<User> users = List.of(
            new User("David", "Platt", "01913478234", "david.platt@corrie.co.uk"),
            new User("Jason", "Grimshaw", "01913478123", "jason.grimshaw@corrie.co.uk")
    );

    private IOHandler ioHandler = new IOHandler();
    private JSONHandler jsonHandler = new JSONHandler(ioHandler);


    public UserControllerTest() throws IOException {
        this.ioHandler.IOWriter(users);
    }

    @Before
    public void before() throws IOException {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(jsonHandler)).build();
    }

    @Test
    public void itShouldListAllUsers() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new Gson().toJson(users)));
    }

    @Test
    public void itShouleBeAbleToViewAnExistentUser() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/view/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new Gson().toJson(users.get(0))));
    }

    @Test
    public void itShouleBeUnableToViewAnNonExistentUser() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/view/20"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value(new Gson().toJson("Can't find this user!")));
    }

    @Test
    public void itShouldAddANewUser() throws Exception{
        User new_user = new User("Alice", "Cooper", "073927583", "alice.cooper@gmail.com");
        int users_size = users.size();
        this.mockMvc.perform(MockMvcRequestBuilders.post("/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(new_user))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value(new Gson().toJson("Successed")));
        List<User> new_users = ioHandler.IOReader();
        assertThat(new_users.size()).isEqualTo(users_size + 1);
    }


    @Test
    public void itShouldBeAbleToEditAnExistentUser() throws Exception{
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("firstName", "Bob");
        this.mockMvc.perform(MockMvcRequestBuilders.put("/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value(new Gson().toJson("Successed")));
        List<User> new_users = ioHandler.IOReader();
        assertThat(new_users.get(0).getFirst_name()).isEqualTo("Bob");
    }

    @Test
    public void itShouldBeUnableToEditAnExistentUser() throws Exception{
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("firstName", "Bob");
        this.mockMvc.perform(MockMvcRequestBuilders.put("/20")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value(new Gson().toJson("Can't find this user!")));
    }

    @Test
    public void itShouldBeAbleToDeleteAnExistentUser() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value(new Gson().toJson("Successed")));
    }

    @Test
    public void itShouldBeUnableToDeleteAnNonExistentUser() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/20"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value(new Gson().toJson("Can't find this user!")));
    }
}
