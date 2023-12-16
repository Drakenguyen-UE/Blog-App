package com.vti.blogapp.form;

import com.vti.blogapp.validation.PostTitleNotExist;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class PostUpdateForm {
    @NotBlank
    @Length(max = 50)
    @PostTitleNotExist
    private String title;

    @NotBlank
    @Length(max = 100)
    private String description;

    @NotBlank
    @Length(max = 150)
    private String content;
}
