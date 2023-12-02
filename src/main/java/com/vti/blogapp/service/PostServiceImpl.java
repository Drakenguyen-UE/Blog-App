package com.vti.blogapp.service;

import com.vti.blogapp.dto.PostDto;
import com.vti.blogapp.form.PostCreateForm;
import com.vti.blogapp.mapper.PostMapper;
import com.vti.blogapp.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    @Override
    public PostDto create(PostCreateForm form) {
        var post = PostMapper.map(form);
        var savePost = postRepository.save(post);
        return null;
    }
}
