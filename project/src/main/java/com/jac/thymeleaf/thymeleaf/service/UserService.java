package com.jac.thymeleaf.thymeleaf.service;

import com.jac.thymeleaf.thymeleaf.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> findAll();
    Optional<UserEntity> findById(Long id);
    UserEntity save(UserEntity user);
    void deleteById(Long id);
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
}