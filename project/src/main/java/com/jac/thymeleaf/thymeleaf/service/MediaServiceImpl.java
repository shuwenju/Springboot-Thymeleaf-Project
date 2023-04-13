package com.jac.thymeleaf.thymeleaf.service;

import com.jac.thymeleaf.thymeleaf.entity.CommentEntity;
import com.jac.thymeleaf.thymeleaf.entity.PostEntity;
import com.jac.thymeleaf.thymeleaf.mapper.MapperHelper;

import com.jac.thymeleaf.thymeleaf.model.CommentModel;
import com.jac.thymeleaf.thymeleaf.model.PostModel;
import com.jac.thymeleaf.thymeleaf.repository.CommentRepository;
import com.jac.thymeleaf.thymeleaf.repository.PostRepository;
import com.jac.thymeleaf.thymeleaf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
                return mapperHelper.convertPostEntityListToPostModelList(postEntities);
            }

        @Override
        public void savePost(PostModel post)
            {
                PostEntity entity = mapperHelper.convertPostModeltoPostEntity(post);
                postRepository.save(entity);
                //when you save a post, you have to save the comment that is associated with it
                //get the comment associated with it
            }


        @Override
        public void saveComment(CommentModel comment) {
            CommentEntity entity = mapperHelper.convertCommentModeltoCommentEntity(comment);
            commentRepository.save(entity);
        }


        @Override
        public List<PostModel> getPostByUserId(Long postByUserId)
            {
                Optional<List<PostEntity>> foundPosts = postRepository.findAllByUserId(postByUserId);
                if (foundPosts.isPresent()) {
                    List<PostEntity> foundPostList = foundPosts.get();
                    if (foundPostList.size() == 1) {
                        return Collections.singletonList(mapperHelper.convertPostEntityToPostModel(foundPostList.get(0)));
                    } else {
                        return mapperHelper.convertPostEntityListToPostModelList(foundPostList);
                    }
                } else {
                    return Collections.emptyList();
                }
            }

        @Override
        public List<CommentModel> getAllCommentByPostId(Long postId) {
            Optional<List<CommentEntity>> foundComments = commentRepository.findByPostId(postId);
            if (foundComments.isPresent()) {
                List<CommentEntity> foundCommentList = foundComments.get();
                if (foundCommentList.size() == 1) {
                    return Collections.singletonList(mapperHelper.convertCommentEntitytoCommentModel(foundCommentList.get(0)));
                } else {
                    return mapperHelper.convertCommentEntityListtoCommentModelList(foundCommentList);
                }
            } else {
                return Collections.emptyList();
            }
        }


        @Override
        public void deletePost(Long postId)
            {
                postRepository.deleteById(postId);
            }


    }
