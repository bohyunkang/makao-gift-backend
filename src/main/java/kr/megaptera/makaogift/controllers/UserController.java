package kr.megaptera.makaogift.controllers;

import jakarta.validation.Valid;
import kr.megaptera.makaogift.dtos.UserCreationDto;
import kr.megaptera.makaogift.dtos.UserDto;
import kr.megaptera.makaogift.dtos.UserRegistrationDto;
import kr.megaptera.makaogift.exceptions.PasswordNotMatch;
import kr.megaptera.makaogift.exceptions.UserNotFound;
import kr.megaptera.makaogift.models.User;
import kr.megaptera.makaogift.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserDto user(
            @RequestAttribute("username") String username
    ) {
        User user = userService.detail(username);

        return user.toDto();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserCreationDto register(
            @Valid @RequestBody UserRegistrationDto userRegistrationDto
    ) {
        String name = userRegistrationDto.getName();
        String username = userRegistrationDto.getUsername();
        String password = userRegistrationDto.getPassword();

        if (!userRegistrationDto.getConfirmPassword().equals(password)) {
            throw new PasswordNotMatch();
        }

        User user = userService.create(name, username, password);

        return user.toCreationDto();
    }

    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotFound() {
        return "User not found!";
    }
}
