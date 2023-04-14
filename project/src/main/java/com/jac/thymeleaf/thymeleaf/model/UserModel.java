package com.jac.thymeleaf.thymeleaf.model;

import com.jac.thymeleaf.thymeleaf.entity.CommentEntity;
import com.jac.thymeleaf.thymeleaf.entity.PostEntity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
@ToString
public class UserModel {
    private Long id;
    @Size(min = 5, max = 15, message = "Username must be between 2 and 15 characters")
    private String username;

    @Pattern(regexp = "^[^@]*@[^@]*$", message = "E-mail invalid")
    private String email;

    @Size(min = 4, max = 12, message = "Password must be between 4 and 12 characters")
    private String password;

    @Size(min = 2, max = 12, message = "First name must be between 2 and 12 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only letters in names")
    private String firstName;

    @Size(min = 4, max = 15, message = "Last name must be between 4 and 15 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only letters in names")
    private String lastName;

    private String sex;
//    private List<PostModel> posts;
//    private List<CommentModel> comments;
}
