package com.vti.blogapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = CommentIdExistsValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommentIdExist {
    String message() default "The comment does not exist.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
