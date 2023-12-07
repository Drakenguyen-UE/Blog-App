package com.vti.blogapp.service;

import com.vti.blogapp.dto.PostDto;
import com.vti.blogapp.entity.Post;
import com.vti.blogapp.form.PostCreateForm;
import com.vti.blogapp.form.PostUpdateForm;
import com.vti.blogapp.mapper.PostMapper;
import com.vti.blogapp.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public Page<PostDto> findAll(Pageable pageable) { // nâng cấp từ List<> lên Page<> truyền vào Pageable
        return postRepository.findAll(pageable)        // nên tìm hiểu lambda và method referrence
                .map(PostMapper::map);
    }

    @Override
    public PostDto findById(Long id) {
        var post = postRepository.findById(id).get();
        return PostMapper.map(post);
    }

    @Override
    public PostDto create(PostCreateForm form) {
        var post = PostMapper.map(form);
        var savePost = postRepository.save(post);
        return PostMapper.map(savePost);
    }

    @Override
    public PostDto update(PostUpdateForm form, Long id) {
        var post = PostMapper.map(form);
        post.setId(id);
        var savedPost = postRepository.save(post);
        return PostMapper.map(savedPost);
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }
}
