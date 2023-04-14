package com.jac.thymeleaf.thymeleaf.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Shuwen Ju
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class PostModel {
    private Long id;
    @NotEmpty(message = "Content cannot be empty")
    @Size(min = 2, max = 255)
    private String content;
    private LocalDateTime createdAt;
    private String formattedDateTime;
    private UserModel user;
//    private List<CommentModel> comments;

    public void formatCreatedAt(LocalDateTime createdAt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mma");
        formattedDateTime = createdAt.format(formatter);
    }
}
