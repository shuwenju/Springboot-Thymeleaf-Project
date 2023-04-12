package com.jac.thymeleaf.thymeleaf.view;

import com.jac.thymeleaf.thymeleaf.entity.CommentEntity;
import com.jac.thymeleaf.thymeleaf.entity.PostEntity;
import lombok.*;

import java.util.List;

/**
 * @author Shuwen Ju
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserView {
    private Long id;
    private String username;
    private String profilePicture;
    private List<PostEntity> posts;
    private List<CommentEntity> comments;
}
