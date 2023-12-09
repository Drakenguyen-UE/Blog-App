package com.vti.blogapp.controller;

import com.vti.blogapp.dto.CommentDto;
import com.vti.blogapp.form.CommentCreateForm;
import com.vti.blogapp.form.CommentUpdateForm;
import com.vti.blogapp.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/api/v1/posts/comments")
    public Page<CommentDto> findAll(Pageable pageable) {
        return commentService.findAll(pageable);
    }

    @PostMapping("/api/v1/posts/{postId}/comments")
    public CommentDto create(@RequestBody CommentCreateForm form, @PathVariable("postId") Long postId) {
        return commentService.create(form, postId);
    }

    @PutMapping("/api/v1/comments/{id}")
    public CommentDto update(@RequestBody CommentUpdateForm form, @PathVariable("id") Long id) {
        return commentService.update(form, id);
    }

    @DeleteMapping("/api/v1/comments/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        commentService.deleteById(id);
    }
}
