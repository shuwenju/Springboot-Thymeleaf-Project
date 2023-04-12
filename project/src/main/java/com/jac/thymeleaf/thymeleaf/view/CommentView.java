package com.jac.thymeleaf.thymeleaf.view;

import com.jac.thymeleaf.thymeleaf.entity.PostEntity;
import com.jac.thymeleaf.thymeleaf.entity.UserEntity;
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
public class CommentView {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private UserView user;
    private PostView post;
}
