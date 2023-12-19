package com.vti.blogapp.validation;

import com.vti.blogapp.repository.PostRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostIdExistsValidator implements ConstraintValidator<PostIdExist, Long> { // KIỂU ĐẦU LÀ TÊN ANNOTATION, Kiểu thứ 2 là kiểu dữ liệu của loại cần check, ví dụ ở đây là biến ID
    private final PostRepository postRepository; // để kiểm tra bài viết có tồn tại theo id hay ko thì cần 1 đối tượng post repository, chỉ cần dùng tầng repo, ko cần tới 2 tầng khác
    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) { // isValid nghĩa là thế nào là hợp lệ
        return postRepository.existsById(id); // đi vào database kiểm tra xem giá trị id của post có tồn tại hay ko
    }
}
