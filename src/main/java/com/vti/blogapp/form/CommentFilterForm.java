package com.vti.blogapp.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentFilterForm {
    private Long postId; // Người dùng nhập vào id bài post sẽ tìm ra tất cả comment cho bài viết đó
}
