package com.jac.thymeleaf.thymeleaf.service;

import com.jac.thymeleaf.thymeleaf.model.PostModel;
import com.jac.thymeleaf.thymeleaf.model.PostModel;

import java.util.List;

public interface PostService
    {
        List<PostModel> getAllPosts();

        void save(PostModel post);

        PostModel getPostByUserId(Long postByUserId);

        void deletePost(Long postId);
    }
