package com.example.test.sub1.Service.impl;

import com.example.test.sub1.Dto.Post;
import com.example.test.sub1.Repository.PostRepository;
import com.example.test.sub1.Service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public List<Post> getPostList(Post dto) {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC,"postDtm"));
    }

    @Override
    public Post getPost(Post dto) {
        return postRepository.getReferenceById(dto.getPostId());
    }

    public Post addPost(Post dto) {
        UUID uuid4 = UUID.randomUUID();
        dto.setPostId(uuid4.toString());
        return postRepository.save(dto);
    }

    @Override
    public void delPost(Post dto) {
        postRepository. deleteById(dto.getPostId());
    }

    @Override
    public void uptPost(Post dto) {
        postRepository.save(dto);
    }
}
