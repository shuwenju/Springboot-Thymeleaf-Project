package com.jac.thymeleaf.thymeleaf.service;

import com.jac.thymeleaf.thymeleaf.entity.PostEntity;
import com.jac.thymeleaf.thymeleaf.mapper.MapperHelper;
import com.jac.thymeleaf.thymeleaf.model.Post;
import com.jac.thymeleaf.thymeleaf.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService
    {
        private final PostRepository postRepository;
        private final MapperHelper mapperHelper;

        @Autowired
        public PostServiceImpl(PostRepository postRepository, MapperHelper mapperHelper) {
            this.postRepository = postRepository;
            this.mapperHelper = mapperHelper;
        }

        @Override
        public List<Post> getAllPosts()
            {
                List<PostEntity> postEntities =  postRepository.findAll();
                return mapperHelper.convertPostEntityListToPostList(postEntities);
            }

        @Override
        public void save(Post post)
            {
                PostEntity entity = mapperHelper.convertPostToPostEntity(post);
                postRepository.save(entity);
            }

        @Override
        public Post getPostByUserId(Long postByUserId)
            {
                Optional<PostEntity> foundPost = postRepository.findById(postByUserId);
                return foundPost.map(mapperHelper::convertPostEntityToPost).orElse(null);
            }

        @Override
        public void deletePost(Long postId)
            {
                postRepository.deleteById(postId);
            }


    }
