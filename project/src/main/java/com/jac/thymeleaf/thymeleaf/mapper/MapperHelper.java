package com.jac.thymeleaf.thymeleaf.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jac.thymeleaf.thymeleaf.entity.PostEntity;
import com.jac.thymeleaf.thymeleaf.model.PostModel;
import com.jac.thymeleaf.thymeleaf.model.PostModel;
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

        public List<PostModel> convertPostEntityListToPostList(List<PostEntity> entities){
            List<PostModel> posts = new ArrayList<>();
            for(PostEntity entity: entities){
                posts.add(mapper.convertValue(entity, PostModel.class));
            }
            return posts;
        }

        public PostEntity convertPostToPostEntity(PostModel post){
            return mapper.convertValue(post, PostEntity.class);
        }

        public PostModel convertPostEntityToPost(PostEntity postEntity){
            return mapper.convertValue(postEntity, PostModel.class);
        }
    }
