package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.exceptions.UserNotFound;
import kr.megaptera.makaogift.exceptions.UsernameAlreadyTaken;
import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        User user = new User(name, username);

        user.setInitialAmount();
        user.changePassword(password, passwordEncoder);

        userRepository.save(user);

        return user;
    }
}
