package com.jac.thymeleaf.thymeleaf.repository;

import com.jac.thymeleaf.thymeleaf.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Shuwen Ju
 */
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query("SELECT c FROM CommentEntity c JOIN c.post p WHERE p.id = :postId")
    List<CommentEntity> findByPostId(@Param("postId") Long postId);
}
