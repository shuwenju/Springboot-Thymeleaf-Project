package com.jac.thymeleaf.thymeleaf.model;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Shuwen Ju
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CommentModel {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private UserModel user;
    private PostModel post;
    public String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mma");
        return createdAt.format(formatter);
    }

}
