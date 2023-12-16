package com.vti.blogapp.validation;

import com.vti.blogapp.repository.PostRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostTitleNotExistsValidator implements ConstraintValidator<PostTitleNotExist, String> {
    private final PostRepository postRepository;
    @Override
    public boolean isValid(String title, ConstraintValidatorContext context) { // isValid nghĩa là thế nào là hợp lệ
        return !postRepository.existsByTitle(title); // thêm dấu ! để đảo ngược do đang cần tìm notExistByTitle
    }
}
