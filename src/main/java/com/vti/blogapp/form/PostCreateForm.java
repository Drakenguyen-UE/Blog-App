package com.vti.blogapp.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class PostCreateForm {
    @NotBlank // Yêu cầu ko được để trống
    @Length(max = 50)
    private String title;

    @NotBlank
    @Length(max = 100)
    private String description;

    @NotBlank
    @Length(max = 150)
    private String content;
}
