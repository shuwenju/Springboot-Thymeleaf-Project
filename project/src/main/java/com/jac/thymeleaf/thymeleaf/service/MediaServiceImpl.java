package com.jac.thymeleaf.thymeleaf.service;

import com.jac.thymeleaf.thymeleaf.entity.CommentEntity;
import com.jac.thymeleaf.thymeleaf.entity.PostEntity;
import com.jac.thymeleaf.thymeleaf.exceptions.PostNotFoundException;
import com.jac.thymeleaf.thymeleaf.mapper.MapperHelper;

import com.jac.thymeleaf.thymeleaf.model.CommentModel;
import com.jac.thymeleaf.thymeleaf.model.PostModel;
import com.jac.thymeleaf.thymeleaf.model.UserModel;
import com.jac.thymeleaf.thymeleaf.repository.CommentRepository;
import com.jac.thymeleaf.thymeleaf.repository.PostRepository;
import com.jac.thymeleaf.thymeleaf.repository.UserRepository;
import jakarta.transaction.Transactional;
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
                List<PostEntity> postEntities =  postRepository.findAllByOrderByIdDesc();
                return mapperHelper.convertPostEntityListToPostModelList(postEntities);
            }

        public PostModel findPostById(Long id){
            Optional<PostEntity> foundEntity = postRepository.findById(id);
            if(foundEntity.isPresent()){
                PostModel foundPost = mapperHelper.convertPostEntityToPostModel(foundEntity.get());
                return foundPost;
                }else {
                    throw new PostNotFoundException("Post not found with id " + id);
                }

            }
        @Override
        @Transactional
        public void savePost(PostModel post) // added method to save user first, otherwise it won't save
            {
                PostEntity entity = mapperHelper.convertPostModeltoPostEntity(post);
                userRepository.save(entity.getUser());
                postRepository.save(entity);

            }


        @Override
        @Transactional
        public void saveComment(CommentModel comment) { // somehow had to set Post from pulling from the database again otherwise post id is null, but why it doesn't happen to the user?
            PostEntity postEntity = postRepository.findById(comment.getPost().getId()).get();
            CommentEntity entity = mapperHelper.convertCommentModeltoCommentEntity(comment);
            entity.setPost(postEntity);
            userRepository.save(entity.getUser());
            postRepository.save(entity.getPost());
            commentRepository.save(entity);
        }

        @Override
        public List<PostModel> getAllPostsByUser(UserModel user) {
            List<PostEntity> postEntities = postRepository.findAllByUserId(user.getId()).orElse(new ArrayList<>());
            List<PostModel> postModels = new ArrayList<>();
            for (PostEntity postEntity : postEntities) {
                postModels.add(mapperHelper.convertPostEntityToPostModel(postEntity));
            }
            return postModels;
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
        @Transactional
        public void deletePost(Long postId)
            {
                postRepository.deleteById(postId);
            }


    }
