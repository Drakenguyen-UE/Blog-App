package com.vti.blogapp.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UserCreateForm {
    @NotBlank
    @Length(max = 50)
    private String name;

    @NotBlank
    @Length(max = 20)
    private String username;

    @NotBlank
    @Length(max = 50)
    private String email;

    @NotBlank
    @Length(min = 8, max = 16)
    private String password;
}
