package com.vti.blogapp.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class PostCreateForm {
    @NotBlank(message = "{post.title.NotBlank.message}") // bọc trong ngoặc {} để biết đây là key chứ ko phải value
    @Length(max = 50, message = "{post.title.Length.message}")
    private String title;

    @NotBlank // Yêu cầu ko được để trống
    @Length(max = 100)
    private String description;

    @NotBlank
    @Length(max = 150)
    private String content;
}
