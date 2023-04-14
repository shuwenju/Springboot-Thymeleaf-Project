package com.jac.thymeleaf.thymeleaf.exceptions;

/**
 * @author Shuwen Ju
 */
public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(String message) {
        super(message);
    }
}
