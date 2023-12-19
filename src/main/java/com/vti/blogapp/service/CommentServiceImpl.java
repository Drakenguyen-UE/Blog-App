package com.vti.blogapp.service;

import com.vti.blogapp.dto.CommentDto;
import com.vti.blogapp.form.CommentCreateForm;
import com.vti.blogapp.form.CommentFilterForm;
import com.vti.blogapp.form.CommentUpdateForm;
import com.vti.blogapp.mapper.CommentMapper;
import com.vti.blogapp.repository.CommentRepository;
import com.vti.blogapp.repository.PostRepository;
import com.vti.blogapp.specification.CommentSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository; // postRepo này có trách nhiệm lấy ra bài viết ứng với cái id này, vậy sẽ có đc 1 post entity, sau đó set vào cho comment


    @Override
    public Page<CommentDto> findAll(CommentFilterForm form, Pageable pageable) {
        var spec = CommentSpecification.buildSpec(form); // chuyển hoá thành spec giống bên post
        return commentRepository.findAll(spec, pageable).map(CommentMapper::map); // Phương thức map ở đây là của Page chứ ko phải mình viết trong mapper
    }     // Do khi dùng findAll sẽ trả về 1 page là comment entity, nên phải map để trả về commentDto

    @Override
    public Page<CommentDto> findByPostId(Long postId, Pageable pageable) {
        return commentRepository.findByPostId(postId, pageable).map(CommentMapper::map);
    }

    @Override
    public CommentDto findById(Long id) {
        var comment = commentRepository.findById(id).get();
        return CommentMapper.map(comment);
    }

    @Override
    public CommentDto create(CommentCreateForm form, Long postId) {
        // Bước đầu ánh xạ form này sang entity
        var comment = CommentMapper.map(form);
        // tạo ra bài viết
        var post = postRepository.findById(postId).get();
        comment.setPost(post); // nghĩa l comment này cho bài post này
        // đã có đầy đủ thông tin, tạo comment lưu vào database, kết quả trả về sẽ đặt tên biến là savedComment
        var savedComment = commentRepository.save(comment);
        return CommentMapper.map(savedComment);
    }

    @Override
    public CommentDto update(CommentUpdateForm form, Long id) {
        var comment = commentRepository.findById(id).get();
        CommentMapper.map(form, comment); // tham số đầ vào là form update, tham số t2 là map vào 1 comment tồn tại trong database
        var savedComment = commentRepository.save(comment);
        return CommentMapper.map(savedComment);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByEmail(String email) {
        commentRepository.deleteByEmail(email);
    }
}
