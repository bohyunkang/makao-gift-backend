package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.exceptions.UserNotFound;
import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UserServiceTest {
    private UserService userService;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        passwordEncoder = new Argon2PasswordEncoder(16, 32, 1, 2, 2);

        userRepository = mock(UserRepository.class);

        String username = "boni1234";

        given(userRepository.findByUsername(username))
                .willReturn(Optional.of(User.fake(username)));

        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    void user() {
        String username = "boni1234";

        User user = userService.detail(username);

        verify(userRepository).findByUsername(username);

        assertThat(user.username()).isEqualTo(username);
    }

    @Test
    void userNotFound() {
        String username = "xxx";

        assertThrows(UserNotFound.class, () -> userService.detail(username));
    }

    @Test
    void create() {
        String name = "테스트용";
        String username = "test123";
        String password = "Test1234!";

        Long initialAmount = 50_000L;

        User user = userService.create(name, username, password);

        assertThat(user).isNotNull();
        assertThat(user.amount()).isEqualTo(initialAmount);
        verify(userRepository).save(any());
    }
}
