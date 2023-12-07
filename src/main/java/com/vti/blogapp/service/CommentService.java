package com.vti.blogapp.service;

import com.vti.blogapp.dto.CommentDto;
import com.vti.blogapp.form.CommentCreateForm;

public interface CommentService {
    CommentDto create(CommentCreateForm form, Long postId);
}
