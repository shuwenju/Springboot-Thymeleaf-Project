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
    @Email
    private String email;

    private String password;

}
