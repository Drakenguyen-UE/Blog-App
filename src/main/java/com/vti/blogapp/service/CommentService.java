package com.vti.blogapp.service;

import com.vti.blogapp.dto.CommentDto;
import com.vti.blogapp.entity.Comment;
import com.vti.blogapp.form.CommentCreateForm;
import com.vti.blogapp.form.CommentUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    Page<CommentDto> findAll(Pageable pageable);

    CommentDto create(CommentCreateForm form, Long postId);

    CommentDto update(CommentUpdateForm form, Long id);

    void deleteById(Long id);
}
