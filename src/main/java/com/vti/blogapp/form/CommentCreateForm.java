package com.vti.blogapp.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CommentCreateForm {
//    @NotNull // NOT NULL
//    @NotEmpty // NOT NULL & LENGHTH > 0
    @NotBlank // @ này mạnh nhất nên dùng
    @Length(max = 50)
    private String name;

    @NotBlank
    @Length(max = 75)
    @Email
    private String email;

    @NotBlank
    @Length(max = 100)
    private String body;
}
