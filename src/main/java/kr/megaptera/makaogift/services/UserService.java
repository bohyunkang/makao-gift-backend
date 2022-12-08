package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.exceptions.RegisterFailed;
import kr.megaptera.makaogift.exceptions.UserNotFound;
import kr.megaptera.makaogift.exceptions.UsernameAlreadyTaken;
import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User detail(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFound(username));

        return user;
    }

    public User create(String name, String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyTaken(username);
        }

        if (name.isBlank() || username.isBlank() || password.isBlank()) {
            throw new RegisterFailed();
        }

        if (!Pattern.matches("^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{3,7}$", name)) {
            throw new RegisterFailed();
        }

        if (!Pattern.matches("^[a-z|0-9]{4,14}$", username)) {
            throw new RegisterFailed();
        }

        if (!Pattern.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}", password)) {
            throw new RegisterFailed();
        }

        User user = new User(name, username);

        user.setInitialAmount();
        user.changePassword(password, passwordEncoder);

        userRepository.save(user);

        return user;
    }
}
