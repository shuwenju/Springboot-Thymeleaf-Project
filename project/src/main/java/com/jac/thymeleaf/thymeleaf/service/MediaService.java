package com.jac.thymeleaf.thymeleaf.service;

import com.jac.thymeleaf.thymeleaf.model.CommentModel;
import com.jac.thymeleaf.thymeleaf.model.PostModel;

import java.util.List;

public interface MediaService
    {
        List<PostModel> getAllPosts();

        void savePost(PostModel post);
        void saveComment(CommentModel comment);

        List<PostModel> getPostByUserId(Long userId);
        List<CommentModel> getAllCommentByPostId(Long postId);

        PostModel findPostById(Long id);
        void deletePost(Long postId);
    }
