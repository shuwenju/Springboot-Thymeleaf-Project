package com.jac.thymeleaf.thymeleaf.service;

import com.jac.thymeleaf.thymeleaf.model.Post;

import java.util.List;

public interface PostService
    {
        List<Post> getAllPosts();

        void save(Post post);

        Post getPostByUserId(Long postByUserId);

        void deletePost(Long postId);
    }
