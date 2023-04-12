package com.jac.thymeleaf.thymeleaf.Repositories;

import com.jac.thymeleaf.thymeleaf.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
}
