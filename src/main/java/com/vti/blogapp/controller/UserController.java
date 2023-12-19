package com.vti.blogapp.controller;

import com.vti.blogapp.dto.UserDto;
import com.vti.blogapp.form.UserCreateForm;
import com.vti.blogapp.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/api/v1/users")
    public UserDto create(@RequestBody @Valid UserCreateForm form) {
        return userService.create(form);
    }
}
