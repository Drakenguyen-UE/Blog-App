package com.vti.blogapp.service;

import com.vti.blogapp.dto.PostDto;
import com.vti.blogapp.form.PostCreateForm;
import com.vti.blogapp.form.PostUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PostService {
    Page<PostDto> findAll(Pageable pageable); // Đầu vào luôn là 1 form, đầu ra luôn là DTO
                                              // Nâng cấp từ List<> lên phân trang Page<>

    PostDto findById(Long id);

    PostDto create(PostCreateForm form);

    PostDto update(PostUpdateForm form, Long id);

    void deleteById(Long id);
}
