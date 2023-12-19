package com.vti.blogapp.validation;

import com.vti.blogapp.repository.PostRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostTitleNotExistsValidator implements ConstraintValidator<PostTitleNotExist, String> { // đang áp duụng cho cột title nên lấy kiểu dữ liệu của nó là String
    private final PostRepository postRepository;
    @Override
    public boolean isValid(String title, ConstraintValidatorContext context) { // isValid nghĩa là thế nào là hợp lệ
        return !postRepository.existsByTitle(title); // thêm dấu ! để đảo ngược do đang cần tìm notExistByTitle
                                // do thư viện spring data jpa ko có sẵn phương thức này giống như bên existById nên phải vào PostRepo để tạo phương thức mới method name
    }
}
