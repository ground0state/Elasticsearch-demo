package app.api.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import app.api.controller.UserController;
import app.api.entity.User;
import app.api.service.UserService;

/**
 * abstract controller test.
 * 
 * @author ground0state
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Value("${application.api.user_path}")
    private String apiUserPath;

    @Autowired
    private UserController target;
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    /**
     * Create mock.
     */
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(target).build();
    }

    /**
     * Get test.
     * 
     * @throws Exception if fails
     */
    @Test
    public void getTest() throws Exception {
        long id = ThreadLocalRandom.current().nextLong(1, 100000);
        String name = RandomStringUtils.randomAlphabetic(20);
        User user = new User(id, name);
        Optional<User> optionalUser = Optional.of(user);

        when(service.findById(id)).thenReturn(optionalUser);
        ResultActions ra = mockMvc.perform(get(apiUserPath + "/{id}", id));
        ra.andExpect(status().isOk());
    }
}
