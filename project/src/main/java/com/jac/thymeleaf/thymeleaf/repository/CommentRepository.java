package com.jac.thymeleaf.thymeleaf.repository;

import com.jac.thymeleaf.thymeleaf.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Shuwen Ju
 */
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
