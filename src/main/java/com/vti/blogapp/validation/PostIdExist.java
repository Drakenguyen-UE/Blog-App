package com.vti.blogapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = PostIdExistsValidator.class) // logic được xác thực bởi class này
@Target(ElementType.PARAMETER) // PARAMETER dùng cho các tham số đầu vào của phương thức (PathVariable)
@Retention(RetentionPolicy.RUNTIME)
public @interface PostIdExist {
    String message() default "{post.id.Exists.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
