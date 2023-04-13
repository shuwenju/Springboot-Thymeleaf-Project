package com.jac.thymeleaf.thymeleaf.service;

import com.jac.thymeleaf.thymeleaf.model.CommentModel;
import com.jac.thymeleaf.thymeleaf.model.PostModel;

import java.util.List;

public interface MediaService
    {
        List<PostModel> getAllPosts();

        void save(PostModel post);

        List<PostModel> getPostByUserId(Long userId);
        List<CommentModel> getAllCommentByPostId(Long postId);

        void deletePost(Long postId);
    }
