package com.jac.thymeleaf.thymeleaf.repository;

import com.jac.thymeleaf.thymeleaf.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Shuwen Ju
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
