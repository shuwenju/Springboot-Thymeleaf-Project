package com.jac.thymeleaf.thymeleaf.repository;

import com.jac.thymeleaf.thymeleaf.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    @Query("SELECT p FROM PostEntity p WHERE p.user.id = :userId")
    Optional<List<PostEntity>> findAllByUserId(@Param("userId") Long userId);

    List<PostEntity> findAllByOrderByIdDesc();

}
