package com.jac.thymeleaf.thymeleaf.model;

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
    @Size(min = 3, max = 16, message = "Username must be between 3 and 16 characters")
    private String username;

    @Pattern(regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", message = "E-mail invalid")
    private String email;

    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
    private String password;

    @Size(min = 2, max =30, message = "First name must be between 2 and 30 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only letters in names")
    private String firstName;

    @Size(min = 2, max = 30, message = "Last name must be between 4 and 15 characters")
    @Pattern(regexp = "^[A-Za-z]+([\\\\s]?[A-Za-z]+)*$", message = "Only letters in names")
    private String lastName;

    private String sex;

}
