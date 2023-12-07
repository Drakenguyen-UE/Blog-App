package com.vti.blogapp.repository;

import com.vti.blogapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> { // nhập vào Long là kiểu dữ liệu của khoá chính
}
