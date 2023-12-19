package com.vti.blogapp.service;

import com.vti.blogapp.dto.UserDto;
import com.vti.blogapp.form.UserCreateForm;

public interface UserService {
    UserDto create(UserCreateForm form);
}
