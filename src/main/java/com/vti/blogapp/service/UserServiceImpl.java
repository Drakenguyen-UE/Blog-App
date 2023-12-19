package com.vti.blogapp.service;

import com.vti.blogapp.dto.UserDto;
import com.vti.blogapp.form.UserCreateForm;
import com.vti.blogapp.mapper.UserMapper;
import com.vti.blogapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDto create(UserCreateForm form) {
        var user = UserMapper.map(form);
        var encodedPassword = passwordEncoder.encode(user.getPassword()); // mã hoá password của user rồi lưu vào encodedPassword
        user.setPassword(encodedPassword); // sau khi mã hoá thì gán vào user
        var savedUser = userRepository.save(user);
        return UserMapper.map(savedUser);
    }
}
