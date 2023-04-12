package com.jac.thymeleaf.thymeleaf.model;

import com.jac.thymeleaf.thymeleaf.entity.CommentEntity;
import com.jac.thymeleaf.thymeleaf.entity.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostModel {

        private Long id;

        @NotEmpty(message = "Content cannot be empty")
        @Size(min = 2, max = 255)
        private String content;

        private LocalDateTime createdAt;

        private UserEntity user;

        private CommentEntity comments;
    }
