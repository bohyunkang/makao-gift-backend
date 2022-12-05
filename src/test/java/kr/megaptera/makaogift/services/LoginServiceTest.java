package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.exceptions.LoginFailed;
import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class LoginServiceTest {
    private LoginService loginService;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);

        passwordEncoder = new Argon2PasswordEncoder(16, 32, 1, 2, 2);

        loginService = new LoginService(userRepository, passwordEncoder);
    }

    @Test
    void loginSuccess() {
        String username = "boni1234";
        String password = "Test1234!";

        User user = User.fake(username);
        user.changePassword(password, passwordEncoder);

        given(userRepository.findByUsername(username)).willReturn(Optional.of(user));

        User found = loginService.login(username, password);

        assertThat(found.username()).isEqualTo(username);
    }

    @Test
    void loginWithIncorrectUsername() {
        assertThrows(LoginFailed.class, () -> {
            loginService.login("xxx", "Test1234!");
        });
    }

    @Test
    void loginWithIncorrectPassword() {
        assertThrows(LoginFailed.class, () -> {
            loginService.login("boni1234", "xxx");
        });
    }
}
