package com.jac.thymeleaf.thymeleaf.model;

import com.jac.thymeleaf.thymeleaf.entity.PostEntity;
import com.jac.thymeleaf.thymeleaf.entity.UserEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author Shuwen Ju
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommentModel {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private UserEntity user;
    private PostEntity post;
}
