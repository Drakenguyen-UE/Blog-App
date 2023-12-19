package com.vti.blogapp.service;

import com.vti.blogapp.dto.UserDto;
import com.vti.blogapp.form.UserCreateForm;
import com.vti.blogapp.mapper.UserMapper;
import com.vti.blogapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService { // Riêng User phải cho impl thêm interface UserDetailsService này
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (user == null) { // Nếu ko tìm thấy user thì throw ra lỗi này
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getUsername(), // nếu có thì return User truyền vào tài khoản, password
                user.getPassword(),
                AuthorityUtils.NO_AUTHORITIES); // Tất cả đều là user có quyền hạn giống nhau
    }
}
