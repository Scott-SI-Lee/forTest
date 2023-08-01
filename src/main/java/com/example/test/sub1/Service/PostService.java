package com.example.test.sub1.Service;

import com.example.test.sub1.Dto.Post;

import java.util.List;

public interface PostService {

    List<Post> getPostList(Post dto);

    Post getPost(Post dto);

    Post addPost(Post dto);

    void delPost(Post dto);

    void uptPost(Post dto);
}
