package com.jac.thymeleaf.thymeleaf.view;

import com.jac.thymeleaf.thymeleaf.model.CommentModel;
import com.jac.thymeleaf.thymeleaf.model.UserModel;
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
public class PostView {
    private Long id;
    private String content;
    private String formattedDateTime;
    private UserView user;
    private List<CommentView> comments;

    public void formatCreatedAt(LocalDateTime createdAt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mma");
        formattedDateTime = createdAt.format(formatter);
    }
}
