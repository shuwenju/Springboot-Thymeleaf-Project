package com.jac.thymeleaf.thymeleaf.Repositories;

import com.jac.thymeleaf.thymeleaf.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<CommentEntity, Long> {
}
