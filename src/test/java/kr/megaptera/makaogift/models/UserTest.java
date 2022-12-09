package kr.megaptera.makaogift.models;

import kr.megaptera.makaogift.exceptions.NotEnoughAmount;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {

    @Test
    void creationAndSetInitialAmount() {
        String name = "강보니";
        String username = "boni1234";
        User user = new User(name, username);

        user.setInitialAmount();

        assertThat(user.name()).isEqualTo(name);
        assertThat(user.username()).isEqualTo(username);
        assertThat(user.getAmount()).isEqualTo(50_000L);
    }

    @Test
    void order() {
        User user = new User();
        user.setInitialAmount();
        Long initialAmount = user.amount();

        Product product = Product.fake(1L);
        Integer quantity = 3;

        user.order(product, quantity);

        assertThat(user.amount()).isEqualTo(initialAmount - product.getPrice() * quantity);
    }

    @Test
    void orderWithNotEnoughAmount() {
        User user = new User();
        user.setInitialAmount();
        Long initialAmount = user.amount();

        Product product = Product.fake(1L);
        Integer quantity = 100;

        assertThrows(NotEnoughAmount.class, () -> user.order(product, quantity));

        assertThat(user.amount()).isEqualTo(initialAmount);
    }

    @Test
    void authenticate() {
        PasswordEncoder passwordEncoder = new Argon2PasswordEncoder(16, 32, 1, 2, 2);

        User user = User.fake("boni1234");
        String password = "Test1234!";

        user.changePassword(password, passwordEncoder);

        assertThat(user.authenticate(password, passwordEncoder)).isTrue();
        assertThat(user.authenticate("xxx", passwordEncoder)).isFalse();
    }
}
