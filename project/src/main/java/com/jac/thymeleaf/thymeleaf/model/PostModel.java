package com.jac.thymeleaf.thymeleaf.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Shuwen Ju
 */
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
    private UserModel user;
    private List<CommentModel> comments;
}
