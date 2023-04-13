package com.jac.thymeleaf.thymeleaf.service;

import com.jac.thymeleaf.thymeleaf.entity.PostEntity;
import com.jac.thymeleaf.thymeleaf.mapper.MapperHelper;

import com.jac.thymeleaf.thymeleaf.model.PostModel;
import com.jac.thymeleaf.thymeleaf.repository.CommentRepository;
import com.jac.thymeleaf.thymeleaf.repository.PostRepository;
import com.jac.thymeleaf.thymeleaf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediaServiceImpl implements MediaService
    {
        private final PostRepository postRepository;
        private final CommentRepository commentRepository;
        private final UserRepository userRepository;
        private final MapperHelper mapperHelper;

        @Autowired
        public MediaServiceImpl(PostRepository postRepository, CommentRepository commentRepository,
                                UserRepository userRepository, MapperHelper mapperHelper) {
            this.postRepository = postRepository;
            this.commentRepository = commentRepository;
            this.userRepository = userRepository;
            this.mapperHelper = mapperHelper;
        }

        @Override
        public List<PostModel> getAllPosts()
            {
                List<PostEntity> postEntities =  postRepository.findAll();
                return mapperHelper.convertPostEntityListToPostList(postEntities);
            }

        @Override
        public void save(PostModel post)
            {
                PostEntity entity = mapperHelper.convertPostToPostEntity(post);
                postRepository.save(entity);
            }

//        @Override
//        public PostModel getPostByUserId(Long postByUserId)
//            {
//                Optional<PostEntity> foundPost = postRepository.findById(postByUserId);
//                return foundPost.map(mapperHelper::convertPostEntityToPost).orElse(null);
//            }

        @Override
        public void deletePost(Long postId)
            {
                postRepository.deleteById(postId);
            }


    }
