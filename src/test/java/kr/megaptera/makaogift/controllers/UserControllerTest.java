package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.exceptions.UserNotFound;
import kr.megaptera.makaogift.exceptions.UsernameAlreadyTaken;
import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.services.UserService;
import kr.megaptera.makaogift.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@ActiveProfiles("test")
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @SpyBean
    private JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    void setup() {
        token = jwtUtil.encode("boni1234");
    }

    @Test
    void user() throws Exception {
        given(userService.detail(any()))
                .willReturn(User.fake("boni1234"));

        mockMvc.perform(MockMvcRequestBuilders.get("/users/me")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"username\":\"boni1234\"")
                ));
    }

    @Test
    void userNotFound() throws Exception {
        given(userService.detail("boni1234"))
                .willThrow(new UserNotFound("boni1234"));

        mockMvc.perform(MockMvcRequestBuilders.get("/users/me")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isNotFound());
    }

    @Test
    void userWithoutAccessToken() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/me"))
                .andExpect(status().isBadRequest());

    }

    @Test
    void register() throws Exception {
        given(userService.create(any(), any(), any()))
                .willReturn(User.fake("boni1234"));

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"강보니\"," +
                                "\"username\":\"boni1234\"," +
                                "\"password\":\"Test1234!\"," +
                                "\"confirmPassword\":\"Test1234!\"" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString("\"username\":\"boni1234\"")
                ));
    }

    @Test
    void registerWithAlreadyTakenUsername() throws Exception {
        given(userService.detail(any()))
                .willReturn(User.fake("boni1234"));

        given(userService.create(any(), any(), any()))
                .willThrow(new UsernameAlreadyTaken("boni1234"));

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"강보니\"," +
                                "\"username\":\"boni1234\"," +
                                "\"password\":\"Test1234!\"," +
                                "\"confirmPassword\":\"Test1234!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }
}
