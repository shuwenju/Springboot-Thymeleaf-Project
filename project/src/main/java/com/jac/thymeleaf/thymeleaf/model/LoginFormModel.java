package com.jac.thymeleaf.thymeleaf.model;

import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class LoginFormModel {
    @Email(message = "Please enter a valid email address")
    private String email;

    @Size(min = 4, message = "Password must be at least 8 characters long")
    private String password;

}
