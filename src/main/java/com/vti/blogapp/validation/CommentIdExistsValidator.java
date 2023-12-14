package com.vti.blogapp.validation;

import com.vti.blogapp.repository.CommentRepository;
import com.vti.blogapp.repository.PostRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommentIdExistsValidator implements ConstraintValidator<CommentIdExist, Long> { // KIỂU ĐẦU LÀ TÊN ANNOTATION, Kiểu thứ 2 là kiểu dữ liệu của biến ID
    private final CommentRepository commentRepository; // chỉ cần dùng tầng repo, ko cần tới 2 tầng khác
    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) { // isValid nghĩa là thế nào là hợp lệ
        return commentRepository.existsById(id); // đi vào database kiểm tra xem giá trị id của comment có tồn tại hay ko
    }
}
