package com.jac.thymeleaf.thymeleaf.mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jac.thymeleaf.thymeleaf.entity.CommentEntity;
import com.jac.thymeleaf.thymeleaf.entity.PostEntity;
import com.jac.thymeleaf.thymeleaf.entity.UserEntity;
import com.jac.thymeleaf.thymeleaf.model.CommentModel;
import com.jac.thymeleaf.thymeleaf.model.PostModel;
import com.jac.thymeleaf.thymeleaf.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;


@Component
public class MapperHelper
    {
        private final ObjectMapper mapper;

        @Autowired
        public MapperHelper(ObjectMapper mapper) {
            this.mapper = mapper;
        }



        public UserModel convertUserEntitytoModel(UserEntity userEntity){
            return mapper.convertValue(userEntity, UserModel.class);
        }
        public UserEntity convertUserModeltoEntity(UserModel userModel){
            return mapper.convertValue(userModel, UserEntity.class);
        }

        public List<PostModel> convertPostEntityListToPostModelList(List<PostEntity> entities){
            List<PostModel> postModelList = new ArrayList<>();
            for(PostEntity entity: entities){
                postModelList.add(convertPostEntityToPostModel(entity));
            }
            return postModelList;
        }


        public PostModel convertPostEntityToPostModel(PostEntity postEntity){
            UserModel userModel = convertUserEntitytoModel(postEntity.getUser());
            PostModel postModel = new PostModel();
            postModel.setId(postEntity.getId());
            postModel.setContent(postEntity.getContent());
            postModel.setCreatedAt(postEntity.getCreatedAt());
            postModel.setUser(userModel);
            return postModel;
        }

        public PostEntity convertPostModeltoPostEntity(PostModel postModel){
            UserEntity userEntity = convertUserModeltoEntity(postModel.getUser());
            PostEntity entity = PostEntity.builder()
                    .content(postModel.getContent())
                    .createdAt(postModel.getCreatedAt())
                    .user(userEntity)
                    .build();
            return entity;
        }

        public CommentModel convertCommentEntitytoCommentModel(CommentEntity commentEntity){
            PostModel postModel = convertPostEntityToPostModel(commentEntity.getPost());
            UserModel userModel = convertUserEntitytoModel(commentEntity.getUser());
            CommentModel commentModel = CommentModel.builder()
                    .id(commentEntity.getId())
                    .content(commentEntity.getContent())
                    .createdAt(commentEntity.getCreatedAt())
                    .user(userModel)
                    .post(postModel)
                    .build();
            return commentModel;
        }

        public CommentEntity convertCommentModeltoCommentEntity(CommentModel commentModel){
            PostEntity postEntity = convertPostModeltoPostEntity(commentModel.getPost());
            UserEntity userEntity = convertUserModeltoEntity(commentModel.getUser());
            CommentEntity commentEntity = CommentEntity.builder()
                    .id(commentModel.getId())
                    .content(commentModel.getContent())
                    .createdAt(commentModel.getCreatedAt())
                    .user(userEntity)
                    .post(postEntity).build();
            return commentEntity;
        }

        public List<CommentModel> convertCommentEntityListtoCommentModelList(List<CommentEntity> commentEntityList){
            List<CommentModel> commentModelList = new ArrayList<>();
            for(CommentEntity entity: commentEntityList){
                commentModelList.add(convertCommentEntitytoCommentModel(entity));
            }
            return commentModelList;
        }
    }
