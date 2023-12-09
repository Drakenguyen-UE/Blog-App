package com.vti.blogapp.repository;

import com.vti.blogapp.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> { // nhập vào Long là kiểu dữ liệu của khoá chính
    // findBy, countBy, existsBy, deleteBy
    // List<Comment> findByEmail(String email);
    // List<Comment> findByNameAndEmail(String name, String email);
    Page<Comment> findByPostId(Long postId, Pageable pageable);
}
